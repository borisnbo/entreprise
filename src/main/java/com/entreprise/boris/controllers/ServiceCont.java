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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entreprise.boris.models.Service;
import com.entreprise.boris.service.ServiceServ;

@RestController
@CrossOrigin
public class ServiceCont {

	@Autowired
	private ServiceServ serv;
	
	@PostMapping("/addService")
	public Service addService(@RequestBody Service service) {
		return serv.saveService(service);
	}
	
	@GetMapping("/services")
	public List<Service> getService(){
		return serv.GetServices();
	}
	
	@GetMapping("/service/{id}")
	public Service searchById(@PathVariable int id) {
		return serv.GetService(id);
	}
	
	@DeleteMapping("/deleteServ/{id}")
	public String deleteServ(@PathVariable int id) {
		return serv.DeleteServ(id);
	}
	
	@PutMapping("/updateServ")
	public Service updateServ(@RequestBody Service service) {
		return serv.UpdateServ(service);
	}
}
