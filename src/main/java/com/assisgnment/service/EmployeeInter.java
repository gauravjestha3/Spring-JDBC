package com.assisgnment.service;
import java.util.ArrayList;

import com.assisgnment.model.Modelview;
import com.assisgnment.model.ViewModel;

public interface EmployeeInter {
	public ArrayList<Modelview> getAllEmployee();

	public Modelview saveEmployee(ViewModel viewmodel) ;
	public ArrayList<Modelview>searchEmployee(String firstname);
	public ArrayList<Modelview>searchID(String id);
}
