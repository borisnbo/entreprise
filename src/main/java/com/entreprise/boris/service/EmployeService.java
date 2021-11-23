package com.entreprise.boris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entreprise.boris.models.Employe;
import com.entreprise.boris.repos.EmployeRepos;

@Service
public class EmployeService {

	@Autowired
	private EmployeRepos employeRepos;
	
	public Employe addemploye(Employe employe) {
		return employeRepos.save(employe);
	}
	
	public List<Employe> getemployes(){
		return employeRepos.findAll();
	}
	
	public Employe findEmployeById(int id) {
		return employeRepos.findById(id).orElse(null);
	}
	
	public String DeleteEmploye(int id) {
		employeRepos.deleteById(id);
		return "employe suprimer !! "+id;
	}

	public Employe authentic(String username, String pass) {
		Employe emp = employeRepos.findByUsername(username);
		if(emp!=null) {
			if (emp.getPassword() == pass) {
				return emp;
			}
		}
		return null;
	}
}
