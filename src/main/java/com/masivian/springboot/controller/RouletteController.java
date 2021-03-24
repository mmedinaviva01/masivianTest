package com.masivian.springboot.controller;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.springboot.entity.Bet;
import com.masivian.springboot.entity.Roulette;
import com.masivian.springboot.service.IRouletteService;


@RestController
@CrossOrigin
@RequestMapping("/roulette")
public class RouletteController {
	 
	@Autowired
	private IRouletteService rouletteService;
	private GenericResponse genericResponse = GenericResponse.newInstance();
	
	@GetMapping("")
	public ResponseEntity<?> findRouletteAll() {
		return genericResponse.buildResponse(rouletteService.findAll());
	}
	@PostMapping("/create")
	public ResponseEntity<?> save() {
		return genericResponse.buildResponse(rouletteService.save(null));
	}
	@PostMapping("/createBet/{rouletteId}")
	public ResponseEntity<?> createBet(@PathVariable String rouletteId, @RequestBody Bet bet, @RequestHeader String customerId){
		try {
			return ResponseEntity.ok(rouletteService.bet(rouletteId, customerId, bet));
		} catch (Exception e) {
			return  genericResponse.buildResponse("{\"error\":" + "\"" + e.getMessage() + "\"}");
		}
	}
	@PutMapping("/opening/{id}")
	public ResponseEntity<?> opening(@PathVariable String id){
		try {
			return (ResponseEntity<Roulette>) ResponseEntity.ok().body(rouletteService.opening(id));
		} catch (Exception e) {
			return  genericResponse.buildResponse("{\"error\":" + "\"" + e.getMessage() + "\"}");
		}
	}
	
	@PutMapping("/closing/{id}")
	public ResponseEntity<?> closing(@PathVariable String id){
		try {
			return genericResponse.buildResponse(rouletteService.closing(id));
		} catch (Exception e) {
			return  genericResponse.buildResponse("{\"error\":" + "\"" + e.getMessage() + "\"}");
		}
	}
	
}
