package com.gft.mvc.security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gft.mvc.security.model.User;
import com.gft.mvc.security.repository.UserRepository;

@Component
public class StartApplication implements CommandLineRunner {
	
    @Autowired
    private UserRepository repository;
    
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        
    	User user = repository.findByUsername("admin@gft.com");
        
    	if(user == null) {
            user = new User();
            
            user.setEmail("admin@gft.com");
            user.setPassword("Gft@1234");
            user.getRoles().add("MANAGERS");
            repository.save(user);
        }
    	
        user = repository.findByUsername("user@gft.com");
        
        if(user == null) {
            user = new User();
            
            user.setEmail("usuario@gft.com");
            user.setPassword("Gft@1234");
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }
}