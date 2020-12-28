package it.solvingteam.padelmanagement.dto;

import java.util.List;

public class ErrorDto {

    
    private List <String> errors;
    

    

	public ErrorDto() {
		super();
	}


	public ErrorDto(List<String> errors) {
		super();
		this.errors = errors;
	}


	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


	
}
