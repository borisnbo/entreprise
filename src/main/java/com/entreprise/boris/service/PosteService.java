package com.entreprise.boris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entreprise.boris.models.Poste;
import com.entreprise.boris.repos.PosteRepos;

@Service
public class PosteService {

	@Autowired
	private PosteRepos posteRepos;
	
	public Poste addPoste(Poste poste) {
		return posteRepos.save(poste);
	}
	
	public List<Poste> getPostes(){
		return posteRepos.findAll();
	}
	
	public Poste findPostById(int id) {
		return posteRepos.findById(id).orElse(null);
	}
	
	public String DeletePoste(int id) {
		posteRepos.deleteById(id);
		return "Poste suprimer !! "+id;
	}
}
