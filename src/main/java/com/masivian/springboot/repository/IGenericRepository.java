package com.masivian.springboot.repository;

import java.util.List;

public interface IGenericRepository <T>{
	T save(T object);
	List<T> findAll();
	T findById(String id);
	String delete(String id);
}
