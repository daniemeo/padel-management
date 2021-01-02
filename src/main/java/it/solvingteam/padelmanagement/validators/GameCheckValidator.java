package it.solvingteam.padelmanagement.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;

@Component
public class GameCheckValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return GameCheckDto.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GameCheckDto gameCheckDto = (GameCheckDto) target;

		if (!StringUtils.isBlank(gameCheckDto.getDate())) {
			try {
				if (LocalDate.parse(gameCheckDto.getDate()).isBefore(LocalDate.now())) {
					errors.rejectValue("date", "dateError", "Invalid date");
				}
			} catch (Exception e) {
				errors.rejectValue("date", "dateError", "Invalid date");
			}

		}
		if(gameCheckDto.getSlots().size() < 3) {
			errors.rejectValue("slots", "slotError", "la partita deve durare almeno un'ora e mezza");
		}

	}
}
