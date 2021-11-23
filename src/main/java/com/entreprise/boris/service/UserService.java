package com.entreprise.boris.service;

import java.util.List;

import com.entreprise.boris.models.Role;
import com.entreprise.boris.models.User;

public interface UserService {

	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String rolename);
	List<User> getUsers();
	
}
