package com.masivian.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.springboot.entity.Customer;
import com.masivian.springboot.entity.Roulette;

@Repository
public class CustomerRepository extends GenericRepository<Customer>{

	@Autowired
	private RedisTemplate template;
	public Customer save(Customer customer) {
		getTemplate().opsForHash().put(this.getTypeName().getSimpleName(), customer.getId(), customer);
		return customer;
	}
	
	public Customer update(Customer customer) {
		save(customer);
		return customer;
	}
}
