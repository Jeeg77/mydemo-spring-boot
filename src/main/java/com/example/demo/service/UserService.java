package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class UserService {
	
	@Autowired
	public AppUserRepository repo;
	
    public AppUser findUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("Finding user: " + username);
    	AppUser user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	return user;
	}
    
    public AppUser saveUser(AppUser user) throws Exception {
    	System.out.println("Updating user: " + user.toString());
    	return repo.save(user);
    }

}
