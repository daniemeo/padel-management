package it.solvingteam.padelmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;



@ControllerAdvice(basePackages = "it.solvingteam.eserciziospringrest.controller")
public class ExceptionAdvisor {


	
//	@ExceptionHandler(IdException.class)
//	public ResponseEntity<ErrorDto> handleException(IdException e) {
//		List<String> errors = new ArrayList<String>();
//		errors.add(e.getError());
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(errors));
//	}

//	@ExceptionHandler(BindingResultException.class)
//	public ResponseEntity<ErrorDto> handleException(BindingResultException e) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new ErrorDto(e.getBindingResult().getFieldErrors().stream()
//						.map(obj -> obj.getField() + ": " + obj.getDefaultMessage()).collect(Collectors.toList())));
//		
////		List<String> errors = new ArrayList<String>();
////		errors.add(e.getMessage());
////		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(errors));
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDto> handleException(Exception e) {
//		List<String> errors = new ArrayList<String>();
//		errors.add(e.getMessage());
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(errors));
//	}
//	
}
