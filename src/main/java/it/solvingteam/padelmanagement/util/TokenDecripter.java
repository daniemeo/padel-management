package it.solvingteam.padelmanagement.util;

import org.springframework.security.core.context.SecurityContextHolder;

import it.solvingteam.padelmanagement.model.UserPrincipal;

public class TokenDecripter {
	public static String decripter() {
		String username= ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return username;
	}

}
