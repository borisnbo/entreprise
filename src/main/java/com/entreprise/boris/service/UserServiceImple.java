package com.entreprise.boris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entreprise.boris.models.Role;
import com.entreprise.boris.models.User;
import com.entreprise.boris.repos.RoleRepos;
import com.entreprise.boris.repos.UserRepos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImple implements UserService, UserDetailsService {

	@Autowired
	private UserRepos userRepos;
	@Autowired
	private RoleRepos roleRepos;

	@Bean 
	PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepos.findByusername(username);
		if (user==null) {
			throw new UsernameNotFoundException("User not found with the username "+username);
		}
		List<SimpleGrantedAuthority> autorities = new ArrayList<>(); 
		user.getRoles().forEach(role->{
			autorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), autorities);
	}

	
	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncode().encode(user.getPassword()));
		return userRepos.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepos.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		User user = userRepos.findByusername(username);
		Role role = roleRepos.findByName(rolename);
		user.getRoles().add(role);
	}

	@Override
	public List<User> getUsers() {
		return userRepos.findAll();
	}

}
