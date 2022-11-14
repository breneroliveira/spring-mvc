package com.gft.mvc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private SecurityDatabaseService securityService;
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	            .antMatchers("/").permitAll()
	            .antMatchers(HttpMethod.POST, "/login").permitAll()
	            
	            .antMatchers("/managers").hasAnyRole("MANAGERS")
	            .antMatchers("/users").hasAnyRole("USERS", "MANAGERS")
	            
	            .antMatchers("/receita").hasAnyRole("USERS", "MANAGERS")
	            .antMatchers("/ingrediente").hasAnyRole("USERS", "MANAGERS")
	            .antMatchers("/unidade-medida").hasAnyRole("USERS", "MANAGERS")
	            
	            .antMatchers("/receita/form").hasAnyRole("MANAGERS")
	            .antMatchers("/ingrediente/form").hasAnyRole("MANAGERS")
	            .antMatchers("/unidade-medida/form").hasAnyRole("MANAGERS")
	            
	            .antMatchers("/receita/editar").hasAnyRole("MANAGERS")
	            .antMatchers("/ingrediente/editar").hasAnyRole("MANAGERS")
	            .antMatchers("/unidade-medida/editar").hasAnyRole("MANAGERS")
	            
	            .antMatchers("/receita/excluir").hasAnyRole("MANAGERS")
	            .antMatchers("/ingrediente/excluir").hasAnyRole("MANAGERS")
	            .antMatchers("/unidade-medida/excluir").hasAnyRole("MANAGERS")
	            
	            .anyRequest().authenticated()
	            .and().formLogin();  
	}
}