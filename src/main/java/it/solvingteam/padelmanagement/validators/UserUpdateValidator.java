package it.solvingteam.padelmanagement.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.service.UserService;

@Component
public class UserUpdateValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UpdateUserDto updateUserDto = (UpdateUserDto) target;

		if (!updateUserDto.getPassword().equals(updateUserDto.getRepeatePassword())) {
			errors.rejectValue("repeatePassword", "passwordsDoesntMatch", "Password doesn't match");
		}

		Optional<User> user = userService.findUserByUSername(updateUserDto.getUsername());

		if (user.isPresent()) {
			if (Long.parseLong(updateUserDto.getId()) != user.get().getId()) {

				errors.rejectValue("username", "usernameAlreadyExists", "Username already exists");
			}
		}

		if (!StringUtils.isBlank(updateUserDto.getDateOfBirth())) {
			try {
				if (new SimpleDateFormat("yyyy-MM-dd").parse(updateUserDto.getDateOfBirth()).after(new Date())) {
					errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
				}
			} catch (ParseException e) {
				errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
			}

		}

	}

}
