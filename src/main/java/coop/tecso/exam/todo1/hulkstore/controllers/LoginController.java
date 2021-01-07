package coop.tecso.exam.todo1.hulkstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.controllers.request.LoginHttpRequest;

@RestController
public class LoginController {

	private AuthenticationManager authenticationManager;
	
	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/api/auth/login")
	public ResponseEntity<?> handle(@RequestBody LoginHttpRequest request) {
	
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
		
		authenticationManager.authenticate(token);
		
		return new ResponseEntity<>(HttpStatus.OK);
			
	}
	
}
