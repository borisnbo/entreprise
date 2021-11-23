package com.entreprise.boris.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entreprise.boris.models.Role;
import com.entreprise.boris.models.User;
import com.entreprise.boris.service.UserServiceImple;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImple userservice; 

	@GetMapping("/users")
	public ResponseEntity<List<User>>getUsers(){
		return ResponseEntity.ok().body(userservice.getUsers());
	}


	@PostMapping("/users")
	public ResponseEntity<User> saveUsers(@RequestBody User user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toString());
		return ResponseEntity.created(uri).body(userservice.saveUser(user));
	}

	@PostMapping("/roles")
	public ResponseEntity<Role> saverole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toString());
		return ResponseEntity.created(uri).body(userservice.saveRole(role));
	}

	@PostMapping("/roles/addtouser")
	public ResponseEntity<?> saveUsers(@RequestBody RoleToUserForm form){
		userservice.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
		
}

class RoleToUserForm{
	private String username;
	private String roleName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
