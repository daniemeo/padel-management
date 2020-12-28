package it.solvingteam.padelmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserDto;
import it.solvingteam.padelmanagement.dto.message.user.UserSigninMessageDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.UserService;
import it.solvingteam.padelmanagement.validators.UserSignupMessageValidator;



@RestController
@RequestMapping("user")
public class UserController {
	
	 @Autowired 
	 UserService userService;
	 
	 @Autowired
	 UserSignupMessageValidator userSignUpMessageValidator;
	
	 @PostMapping("signup")
	 public ResponseEntity<UserDto> signupUser(@Valid @RequestBody InsertUserDto insertUserDto, BindingResult bindingResult)
				throws Exception {
		 userSignUpMessageValidator.validate(insertUserDto, bindingResult);
		 if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
			}
		 UserDto userDto = userService.signup(insertUserDto);
		 return ResponseEntity.status(HttpStatus.OK).body(userDto);
	 }
	 
	 @PostMapping("login")
	 public ResponseEntity<UserDto> signinUser( @Valid @RequestBody UserSigninMessageDto userSigninMessageDto ) throws Exception{
		 UserDto userDto = userService.signIn(userSigninMessageDto.getUsername(),userSigninMessageDto.getPassword());
		 return ResponseEntity.status(HttpStatus.OK).body(userDto);
	 }
}
