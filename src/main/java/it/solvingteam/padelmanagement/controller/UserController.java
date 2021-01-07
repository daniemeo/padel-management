package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.dto.message.game.UpdateGameDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.dto.message.user.UserSigninMessageDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.UserService;
import it.solvingteam.padelmanagement.validators.UserSignupMessageValidator;
import it.solvingteam.padelmanagement.validators.UserUpdateValidator;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserSignupMessageValidator userSignUpMessageValidator;

	@Autowired
	UserUpdateValidator userUpdateValidator;



//	@PostMapping("login")
//	public ResponseEntity<UserDto> signinUser(@Valid @RequestBody UserSigninMessageDto userSigninMessageDto)
//			throws Exception {
//		UserDto userDto = userService.signIn(userSigninMessageDto.getUsername(), userSigninMessageDto.getPassword());
//		return ResponseEntity.status(HttpStatus.OK).body(userDto);
//	}

	@GetMapping("show/{id}")
	public ResponseEntity<UserDto> show(@PathVariable("id") String id) throws Exception, BindingResultException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdUser(id));

	}

	@PutMapping("update")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UpdateUserDto updateUserDto, BindingResult bindingResult)
			throws Exception {

		userUpdateValidator.validate(updateUserDto, bindingResult);
		if (bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		UserDto user = userService.update(updateUserDto);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}
