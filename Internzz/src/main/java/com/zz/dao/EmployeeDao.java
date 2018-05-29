package com.zz.dao;

import java.util.List;

import com.zz.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee>{
	Employee findByName(String name);
	
	List<Employee> findBySort();
	
	List<Employee> findByDepartment(String departmentId);
	
	List<Employee> findPromotedByDepartment();//查询所有符合升职要求的人
	
	void updateLevel(List<Employee> employee);	//对符合要求的人进行升职
	
	List<Employee> findByLikeName(String name);
	
}
