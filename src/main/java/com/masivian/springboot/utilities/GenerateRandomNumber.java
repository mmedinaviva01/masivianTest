package com.masivian.springboot.utilities;

public class GenerateRandomNumber {
	private static int max;
	private static int min;
	public GenerateRandomNumber(int max, int min) {
		GenerateRandomNumber.max = max;
		GenerateRandomNumber.min = min;
	}
	
	public static int generateRandomNumber(){
        return (int) ((Math.random() * ((GenerateRandomNumber.max - GenerateRandomNumber.min) + 1)) + GenerateRandomNumber.min);
    }
}
