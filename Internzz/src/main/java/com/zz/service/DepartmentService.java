package com.zz.service;

import java.util.List;

import com.zz.entity.Department;

public interface DepartmentService {
	List<Department> findAll();
	Department findDepartmentById(String id);
}
