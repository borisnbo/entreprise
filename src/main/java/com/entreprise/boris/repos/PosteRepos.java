package com.entreprise.boris.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entreprise.boris.models.Poste;

public interface PosteRepos extends JpaRepository<Poste, Integer> {

}
