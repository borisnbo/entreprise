package com.entreprise.boris.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entreprise.boris.models.Role;

public interface RoleRepos extends JpaRepository<Role, Integer> {

	Role findByName(String rolename);

}
