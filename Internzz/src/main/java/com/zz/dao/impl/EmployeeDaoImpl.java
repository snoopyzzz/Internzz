package com.zz.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zz.dao.EmployeeDao;
import com.zz.entity.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao{
	

	
    /**
     * 根据用户名查重
     */
	public Employee findByName(String name) {
		String hql = "from Employee where name=?";
		@SuppressWarnings("unchecked")
		List<Employee> userList =  (List<Employee>) super.hibernateTemplate.find(hql, name);
		if(userList.size() > 0){
			// 查到数据返回第一个
			System.out.println(userList.get(0).getName()+"查找成功");
			return userList.get(0);
		}
		return null;
	}


	@Override
	public List<Employee> findBySort(){
		String hql = "from Employee a order by a.level";

		return (List<Employee>) super.find(hql);
	}


	@Override
	public List<Employee> findByDepartment(String departmentId) {
		String hql = "from Employee a where a.department.id = ? order by a.level desc";
		return (List<Employee>) super.find(hql,departmentId);
	}



	
	@Override
	public List<Employee> findPromotedByDepartment() {
		String hql = "from Employee a where ( CURRENT_DATE() - a.promoted_time ) > ANY"
		+ "(select b.requirements from Rating b where a.department.id = b.department.id)";
//		String hql = "from Employee a where ( CURRENT_DATE() - a.entry_time ) > ANY"
//		+ "(select b.requirements from Rating b where a.department.id = b.department.id)";
		return (List<Employee>) super.find(hql);
	}
	
	@Override
	public void updateLevel(List<Employee> employee) {
		for(Employee e : employee) {
			
	    	java.util.Date nDate = new java.util.Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String sDate = sdf.format(nDate);
	        java.sql.Date now = java.sql.Date.valueOf(sDate);
	        System.out.println(e.getName()+"在"+e.getPromoted_time()+"升职啦！");
			
			int pre = e.getLevel();
			e.setLevel(pre+1);
			e.setPromoted_time(now);
			super.update(e);	
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByLikeName(String name) {
		String hql = "from Employee a where a.name like :name";
		//return (List<Employee>) super.hibernateTemplate.find(hql, name);
		Query query = super.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("name", "%"+name+"%");
        return query.list(); 
	}
	
	

	


}
