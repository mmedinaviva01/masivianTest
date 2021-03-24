package com.masivian.springboot.entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Bet")
public class Bet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String itemBet;
	private double money;
	private String resultBet;
	private Customer customer;
	
	public String getItemBet() {
		return itemBet;
	}
	public void setItemBet(String itemBet) {
		this.itemBet = itemBet;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getResultBet() {
		return resultBet;
	}
	public void setResultBet(String resultBet) {
		this.resultBet = resultBet;
	}
	
}
