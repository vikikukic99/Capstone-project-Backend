package com.example.mulino.controller;

import com.example.mulino.dto.RegistrationDTO;
import com.example.mulino.model.User;
import com.example.mulino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	/*
	@GetMapping("/whoami")
	public ResponseEntity<UserDTO> user(Principal principal) {
		User user = this.userService.findByUsername(principal.getName());
		UserDTO userDTO = UserMapper.UserToDTO(user);
		return new ResponseEntity<>(UserMapper.UserToDTO(user), HttpStatus.OK);
	}
*/


	@PostMapping("/signupReceptionist")
	@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<User> addReceptionist(@RequestBody RegistrationDTO registrationDTO) {
		User existUser = this.userService.findByUsername(registrationDTO.getUsername());
		if (existUser != null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		User user = this.userService.save(registrationDTO);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	

}
