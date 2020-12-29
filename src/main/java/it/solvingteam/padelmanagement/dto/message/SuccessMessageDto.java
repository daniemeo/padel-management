package it.solvingteam.padelmanagement.dto.message;

public class SuccessMessageDto {
 private String message;

 
public SuccessMessageDto(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
 
 
}
