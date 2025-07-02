package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.util.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody AuthRequest req) {
    	Authentication auth = null;
    	String token        = "";
    	System.out.println("Login attempt for: " + req.username());
    	
    	String encodedPassword = encoder.encode(req.password);
    	System.out.println("Encoded Password key: " + encodedPassword);
    	
    	try {
	        auth = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(req.username(), req.password())
	        );
	        UserDetails user = (UserDetails) auth.getPrincipal();
	        token            = JwtUtil.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority());

	        System.out.println("Authenticated! Token: " + token);
    	}
    	catch (Exception ex) {
    	    ex.printStackTrace();
    	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Errore login: " + ex.getMessage());
    	}

      
        return ResponseEntity.ok(Map.of("token", token));
    }
    
    
    public record AuthRequest(String username, String password) {}
   
}
