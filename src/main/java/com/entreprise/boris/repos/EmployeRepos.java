package com.entreprise.boris.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entreprise.boris.models.Employe;

public interface EmployeRepos extends JpaRepository<Employe, Integer> {

	Employe findByUsername(String username);

}
