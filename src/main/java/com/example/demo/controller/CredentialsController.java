package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.AppUser;
import com.example.demo.service.UserService;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/manageCredentials")
public class CredentialsController {

	@Autowired
	public UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/changePassowrd")
	ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest req) {

		AppUser update         = null;
		String password        = req.password;
		String newPassword     = req.newPassword;
	
		try {
			
			AppUser user = userService.findUserByUsername(req.username);
			
			if(user == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato.");
			
		
			if(password.equalsIgnoreCase(newPassword))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La nuova password non può essere uguale alla precedente.");
			
			String encodedNewPassword = encoder.encode(newPassword);
			System.out.println("Encoded New Password key: " + encodedNewPassword);
			user.setPassword(encodedNewPassword);
			
			update = userService.saveUser(user);

		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore find user: " + ex.getMessage());
		}

		return ResponseEntity.ok(Map.of("user", update));
	}
	
	public record ChangePasswordRequest(String username, String password, String newPassword) {}

}
