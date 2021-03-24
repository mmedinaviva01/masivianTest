package com.masivian.springboot.enums;

public enum TypeBetEnum {
	NUMBER("number"),
    COLOR("color");

    private String valueBet;
    TypeBetEnum(String valueBet){
        this.valueBet = valueBet;
    }

    public String getValueBet(){
        return this.valueBet;
    }
}
