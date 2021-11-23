package com.entreprise.boris.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entreprise.boris.models.Poste;
import com.entreprise.boris.service.PosteService;

@RestController
@CrossOrigin("*")
public class PosteController {

	@Autowired
	private PosteService posteService;


	@PostMapping("/addPoste")
	public Poste addPoste(@RequestBody Poste poste) {
		return posteService.addPoste(poste);
	}
	
	@GetMapping("/postes")
	public List<Poste> getPostes(){
		return posteService.getPostes();
	}
	
	@GetMapping("/poste/{id}")
	public Poste searchById(@PathVariable int id) {
		return posteService.findPostById(id);
	}
	
	@DeleteMapping("/deletePost/{id}")
	public String deletePost(@PathVariable int id) {
		return posteService.DeletePoste(id);
	}
	
}
