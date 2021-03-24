package com.masivian.springboot.repository;

import java.util.List;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.springboot.entity.Roulette;

@Repository
public abstract class GenericRepository<T> implements IGenericRepository<T> {

	@Autowired
	private RedisTemplate template;
	private Class<T> typeName;

	@SuppressWarnings("unchecked")
	public GenericRepository() {
		this.typeName = (Class<T>)
                ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
	}

	@Override
	public abstract T save(T object);

	@Override
	public List<T> findAll() {
		return template.opsForHash().values(this.typeName.getSimpleName());
	}

	@Override
	public T findById(String id) {
		return (T) template.opsForHash().get(this.typeName.getSimpleName(), id);
	}
	
	@Override
	public String delete(String id) {
		template.opsForHash().delete(this.typeName.getSimpleName(), id);
		return "Item removed";
	}
	public T update(T t) {
		save(t);
		return t;
	}

	public RedisTemplate getTemplate() {
		return template;
	}

	public void setTemplate(RedisTemplate template) {
		this.template = template;
	}

	public Class<T> getTypeName() {
		return typeName;
	}

	public void setTypeName(Class<T> typeName) {
		this.typeName = typeName;
	}
	
}
