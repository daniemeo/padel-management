package it.solvingteam.padelmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
	
	public static boolean isNumber(String numString) {
		try {
			Integer.parseInt(numString);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
//	public static boolean validateMail(String mail) {
//		Pattern p = Pattern.compile(".+@.+\\.[a-z]+", Pattern.CASE_INSENSITIVE);
//		Matcher m = p.matcher(mail);
//		boolean matchFound = m.matches();
//		return matchFound;
//	}
}
