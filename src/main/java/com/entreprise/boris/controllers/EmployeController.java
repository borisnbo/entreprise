package com.entreprise.boris.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import com.entreprise.boris.models.Employe;
import com.entreprise.boris.models.Service;
import com.entreprise.boris.service.EmployeService;
import com.entreprise.boris.service.ServiceServ;

@RestController
@CrossOrigin
@RequestMapping("/me")
public class EmployeController {

	@Autowired
	private EmployeService employeService;

	@PostMapping("/addEmploye")
	public Employe addEmploye(@RequestBody Employe employe) {
		return employeService.addemploye(employe);
	}
	@PostMapping("/auth")
	public Employe auth(@RequestBody String name, String pass) {
		return employeService.authentic(name, pass);
	}
	
	@GetMapping("/employes")
	public List<Employe> getEmployes(){
		return employeService.getemployes();
	}
	
	@GetMapping("/employe/{id}")
	public Employe searchById(@PathVariable int id) {
		return employeService.findEmployeById(id);
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id) {
		return employeService.DeleteEmploye(id);
	}
	
}
