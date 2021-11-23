package com.entreprise.boris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.entreprise.boris.models.Service;
import com.entreprise.boris.repos.ServiceRepos;

@org.springframework.stereotype.Service
public class ServiceServ {

	@Autowired
	private ServiceRepos serviceRepos;

	public Service saveService(Service service) {
		return serviceRepos.save(service);
	}
	
	public List<Service> GetServices(){
		return serviceRepos.findAll();
	}
	
	public Service GetService(int id) {
		return serviceRepos.findById(id).orElse(null);
	}
	
	public String DeleteServ(int id) {
		serviceRepos.deleteById(id);
		return "Service suprimer !! "+id;
	}
	
	public Service UpdateServ(Service service) {
		Service serv = serviceRepos.findById(service.id).orElse(null);
		serv.nom = service.nom;
		serv.description = service.description;
		return serviceRepos.save(serv);
	}
	
}
