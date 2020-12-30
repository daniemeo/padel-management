package it.solvingteam.padelmanagement.validators;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.service.ClubService;
import it.solvingteam.padelmanagement.service.CourtService;

@Component
public class CourtValidatorUpdate implements Validator{
	@Autowired
	CourtService courtService;

	@Autowired 
	ClubService clubService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CourtDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CourtDto courtDto = (CourtDto)target;
		Court court = courtService.findByIdCourt(courtDto.getId());
		Club club = clubService.findById(court.getClub().getId());
		for (Court courtDb : club.getCourts() ) {
		  if(courtDb.getName().equals(courtDto.getName())) {
			  errors.rejectValue("name", "proposalError", "Esiste già un campo con questo nome");
		  }
		}
		for(Game game : court.getGames()) {
			if(game.getDate().isAfter(LocalDate.now())) {
				errors.rejectValue("gameDto", "proposalError", "Esiste già un campo con questo nome");
			}
		}
	}
	
}
