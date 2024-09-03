package com.example.mulino.controller;

import com.example.mulino.dto.LoginDTO;
import com.example.mulino.dto.UserTokenState;
import com.example.mulino.model.User;
import com.example.mulino.service.UserService;
import com.example.mulino.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody LoginDTO authenticationRequest, HttpServletResponse response) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
/*
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody RegistrationDTO registrationDTO) {
		User existUser = this.userService.findByUsername(registrationDTO.getUsername());

		if (existUser != null) {
			throw new ResourceConflictException(registrationDTO.getId(), "Username already exists");
		}
		registrationDTO.setReceptionistRegistration(false);
		User user = this.userService.save(registrationDTO);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
*/
}