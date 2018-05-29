package com.zz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.dao.EmployeeDao;
import com.zz.entity.Employee;
import com.zz.service.EmployeeService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Resource
	private EmployeeDao employeeDao;
	
	@Override
	public Employee findUserByName(String name) {
		
		return employeeDao.findByName(name);
	}

	@Override
	public List<Employee> findAll() {
		
		return employeeDao.findAll();
	}

	@Override
	public List<Employee> findBySort() {
		
		return employeeDao.findBySort();
	}

	@Override
	public List<Employee> findByDepartment(String departmentId) {
		
		return employeeDao.findByDepartment(departmentId);
	}

	@Override
	public List<Employee> findPromotedByDepartment() {
		
		return employeeDao.findPromotedByDepartment();
	}

	@Override
	public void updateLevel(List<Employee> employee) {
		employeeDao.updateLevel(employee);
	}

	@Override
	public List<Employee> findByLikeName(String name) {
		
		return employeeDao.findByLikeName(name);
	}


}
