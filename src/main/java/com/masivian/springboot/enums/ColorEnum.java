package com.masivian.springboot.enums;

public enum ColorEnum {
	ROJO("ROJO"),
	NEGRO("NEGRO");
	
	private String color;
	ColorEnum(String color){
        this.color = color;
    }

    public String getValueBet(){
        return this.color;
    }
}
