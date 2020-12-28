package it.solvingteam.padelmanagement.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.service.NewClubProposalService;
@Component
public class NewClubProposalValidator implements Validator{
	@Autowired
	NewClubProposalService newClubProposalService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertNewClubProposalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		InsertNewClubProposalDto insertNewClubProposalDto = (InsertNewClubProposalDto) o;
		for(NewClubProposal newProposal : newClubProposalService.findNewProposalByUser(insertNewClubProposalDto.getUserDto().getId())) {
			if(newProposal.getProposalStatus().equals(ProposalStatus.PENDING) ) {
				errors.rejectValue("proposalStatus", "proposalError", "Hai già presentato una proposta");
				
			}
			if( newProposal.getProposalStatus().equals(ProposalStatus.APPROVED)){
				errors.rejectValue("proposalStatus", "proposalError", "Sei già amministratore di un circolo");
			}
		}
		
		
	}

}
