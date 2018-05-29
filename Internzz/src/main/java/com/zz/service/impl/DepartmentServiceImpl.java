package com.zz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.dao.DepartmentDao;
import com.zz.entity.Department;
import com.zz.service.DepartmentService;

@Transactional
@Service 
public class DepartmentServiceImpl implements DepartmentService{

	@Resource
	private DepartmentDao departmentDao;
	
	@Override 
	public List<Department> findAll() {
		
		return departmentDao.findAll();
	}
  
	@Override
	public Department findDepartmentById(String id){
		
		return departmentDao.findDepartmentById(id);
	}

}
