package com.zz.dao;

import com.zz.entity.Department;

public interface DepartmentDao extends BaseDao<Department>{
	Department findDepartmentById(String id);
}
