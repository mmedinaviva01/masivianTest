package com.masivian.springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.masivian.springboot.entity.Roulette;

public class GenericResponse {
	
	private static final GenericResponse instance;

	static {
		instance = new GenericResponse();
	}
	
	public static GenericResponse newInstance() {
		return instance;
	}

	private GenericResponse() {

	}
	public ResponseEntity<?> buildResponse(List<?> object) {
		return ResponseEntity.ok().body(object);
	}
	public ResponseEntity<?> buildResponse(Object object) {
		return (ResponseEntity<Object>) ResponseEntity.ok().body(object);
	}
	public ResponseEntity<?> buildResponse(String object) {
		return (ResponseEntity<String>) ResponseEntity.badRequest().body(object);
	}
}