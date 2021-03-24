package com.masivian.springboot.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
public class Roulette implements Serializable{
	@Id
	private String id;
	private boolean status;
	private List<Bet> bet = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<Bet> getBet() {
		return bet;
	}
	public void setBet(List<Bet> bet) {
		this.bet = bet;
	}
	
}
