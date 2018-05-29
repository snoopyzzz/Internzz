package com.zz.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zz.dao.DepartmentDao;
import com.zz.entity.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	
	@Override
	public Department findDepartmentById(String id) {
		String hql = "from Department a where a.id = ?";	
		List<Department> departmentList = (List<Department>)super.find(hql,id);
		if(departmentList.size()>0) {
			System.out.println(departmentList.get(0).getName()+"查找成功！！！！！！！！！！！！！！！！！！！！");
			return departmentList.get(0);
		}
		System.out.println("查找失败~~~~~~~~~~~~~~~~~~~~~~~~~");
		return null;
	}

}
