package com.masivian.springboot.service;

import java.util.List;

import com.masivian.springboot.entity.Bet;
import com.masivian.springboot.entity.Roulette;


public interface IRouletteService {
	public Roulette save(Roulette roulette);

	public List<Roulette> findAll();

	public Roulette findById(String id) throws Exception;

	public String delete(String id) throws Exception;
	
	public Roulette bet(String rouletteId, String customerId, Bet bet) throws Exception;
	
	public Roulette opening(String id) throws Exception;
	
	public Roulette closing(String id) throws Exception;
}
