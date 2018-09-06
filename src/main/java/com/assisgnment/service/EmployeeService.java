package com.assisgnment.service;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assisgnment.Repository.Repo;
import com.assisgnment.model.Modelview;
import com.assisgnment.model.ViewModel;
import com.assisgnment.service.EmployeeInter;


@Service
public  class EmployeeService implements EmployeeInter {
	@Autowired
	private Repo employeerepo;

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	@Override
	public ArrayList<Modelview> getAllEmployee() {

		log.info("In getAllEmp of Service Impl");          
		return employeerepo.getAllEmployee();        
	}

	@Override
	public Modelview saveEmployee(ViewModel viewmodel) {

		log.info("In saveEmp of Service Impl");
		return employeerepo.saveEmp(viewmodel);
	}
	@Override
	public ArrayList<Modelview>searchEmployee(String firstname){
		log.info("in controller");
		return employeerepo.searchEmployee(firstname);
	}
	@Override
	public ArrayList<Modelview>searchID(String id){
		log.info("in id controller");
		return employeerepo.searchID(id);
	}

}

	


