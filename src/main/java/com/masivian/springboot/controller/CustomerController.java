package com.masivian.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.springboot.entity.Customer;
import com.masivian.springboot.service.ICustomerService;


@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	private GenericResponse genericResponse = GenericResponse.newInstance();
	
	@GetMapping
	public ResponseEntity<?> findCustomerAll(){
		return genericResponse.buildResponse(customerService.findAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody Customer customer) {
		return genericResponse.buildResponse(customerService.save(customer));
	}
	
	
}
