package com.masivian.springboot.utilities;

public class IsNumeric {
	private boolean isNumeric;
	public IsNumeric(String number) {
		try {
			Integer.parseInt(number);
			this.setNumeric(true);
		} catch (NumberFormatException nfe) {
			this.setNumeric(false);
		}
	}
	public boolean isNumeric() {
		return isNumeric;
	}
	public void setNumeric(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}
}
