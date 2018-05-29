package com.zz.dao;

import java.util.List;

public interface BaseDao<T> {

	List<T> findAll();

	T findById(int id);

	void update(T t);

	void delete(T t);

	void save(T t);
	
	List<T> find(String hql);
	
	List<T> find(String hql, Object... params);
}
