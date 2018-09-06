package com.assisgnment.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assisgnment.model.Modelview;
import com.assisgnment.model.ViewModel;
import com.assisgnment.service.EmployeeService;

/***
 * Rest Api using JDBC
 * 
 * @author Gaurav Jestha
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/api")  
public class EmployeeController {
	@Autowired
	private EmployeeService employeService;

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	/*
	 * GET All Employee Details
	 */
	@GetMapping("/getAllUser")    //for taking all information this is our api
	public ArrayList<Modelview> getAllEmp() {

		log.info("in getAllEmp of Controller");
		return employeService.getAllEmployee();     //after controller this will pass to our service
	}

	/*
	 * POST new Employee
	 */
	@PostMapping("/saveUser")
	public Modelview saveEmp(@RequestBody ViewModel viewmodel)  {

		log.info("in saveEmp of Controller");
		return employeService.saveEmployee(viewmodel);

	}
	/*
	 * It is used to Search by First Name
	 */
	@GetMapping("/firstname/{firstname}")
	public ArrayList<Modelview> searchEmployee(@PathVariable String firstname){
		log.info("in searchEmployee Controller");
		return employeService.searchEmployee(firstname);
	}
	/*
	 * It is used to search by id
	 * 
	 */
	@GetMapping("/id/{id}")
	public ArrayList<Modelview> searchID(@PathVariable String id){
		log.info("in searchId Controller");
		return employeService.searchID(id);
	}
}
	


