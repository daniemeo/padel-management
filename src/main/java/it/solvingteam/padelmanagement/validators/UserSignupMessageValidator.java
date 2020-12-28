package it.solvingteam.padelmanagement.validators;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import it.solvingteam.padelmanagement.dto.message.user.InsertUserDto;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Component
public class UserSignupMessageValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return InsertUserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	InsertUserDto insertUserDto = (InsertUserDto) o;
       

        if (!insertUserDto.getPassword().equals(insertUserDto.getRepeatePassword())) {
            errors.rejectValue("repeatePassword", "passwordsDoesntMatch", "Password doesn't match");
        }

        Optional<User> user = userService.findUserByUSername(insertUserDto.getUsername());
        if (user.isPresent()) {
            errors.rejectValue("username", "usernameAlreadyExists", "Username already exists");
        }
//        if(!StringUtils.isBlank(insertUserDto.getMailAddress())) {
//    		if(!Util.validateMail(insertUserDto.getMailAddress())) {
//    			errors.rejectValue("mailFormat", "mailError", "Invalid mail format");
//    		}
//    	}
        if(!StringUtils.isBlank(insertUserDto.getDateOfBirth())) {
        	try {
				if(new SimpleDateFormat("yyyy-MM-dd").parse(insertUserDto.getDateOfBirth())
						.after(new Date())){
					errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
				}
			} catch (ParseException e) {
				errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
			}
        	
        	
        }
    }
}



