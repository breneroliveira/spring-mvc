package com.gft.mvc.security.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.mvc.security.model.User;
import com.gft.mvc.security.repository.UserRepository;

@Service
public class SecurityDatabaseService  implements UserDetailsService {
    
	@Autowired
    private UserRepository userRepository;
    
	@Override
    public UserDetails loadUserByUsername(String email) {
		
        User userEntity = userRepository.findByUsername(email);
        
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        
        UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                userEntity.getPassword(),
                authorities);
        return user;
    }
}