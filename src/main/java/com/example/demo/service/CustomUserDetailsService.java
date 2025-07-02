package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	 private AppUserRepository repo;

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 System.out.println("Loading user: " + username);
    	 AppUser user = repo.findByUsername(username)
             .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	 System.out.println("User loaded!: " + username);
         return User.withUsername(user.getUsername())
             .password(user.getPassword())
             .roles(user.getRole().replace("ROLE_", ""))
             .build();
     }

}
