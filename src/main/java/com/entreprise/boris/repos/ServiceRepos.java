package com.entreprise.boris.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entreprise.boris.models.Service;

public interface ServiceRepos extends JpaRepository<Service, Integer> {

}
