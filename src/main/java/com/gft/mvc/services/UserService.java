package com.gft.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.security.model.User;
import com.gft.mvc.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	public User salvarUser(User user) {
		
		user.getRoles().add("USERS");
		
		return userRepository.save(user);
	}
}