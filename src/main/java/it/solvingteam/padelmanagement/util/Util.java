package it.solvingteam.padelmanagement.util;

public class Util {
	
	public static boolean isNumber(String numString) {
		try {
			Integer.parseInt(numString);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
}
