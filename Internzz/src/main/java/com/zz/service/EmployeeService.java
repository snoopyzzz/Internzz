package com.zz.service;

import java.util.List;


import com.zz.entity.Employee;


public interface EmployeeService {
	Employee findUserByName(String name);
	List<Employee> findAll();
	List<Employee> findBySort();
	List<Employee> findByDepartment(String departmentId);
	
	List<Employee> findPromotedByDepartment();//查找符合升职要求的人
	
	void updateLevel(List<Employee> promotedList);	//对符合要求的人进行升职

	List<Employee> findByLikeName(String name);

}
