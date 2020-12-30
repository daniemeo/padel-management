package it.solvingteam.padelmanagement.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.service.ClubService;
import it.solvingteam.padelmanagement.service.CourtService;

@Component
public class CourtValidator implements Validator{
	@Autowired
	CourtService courtService;
	@Autowired 
	ClubService clubService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertCourtDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InsertCourtDto insertCourtDto = (InsertCourtDto)target;
		Club club = clubService.findClubByAdmin(Long.parseLong(insertCourtDto.getAdminId()));
		 for (Court court : club.getCourts()) {
			 Court courtClub = courtService.findById(court.getId());
			 if(courtClub.getName().equals(insertCourtDto.getName())) {
				 errors.rejectValue("name", "proposalError", "Esiste già un campo con questo nome");
			 }
			 
		 }
		
	}

}
