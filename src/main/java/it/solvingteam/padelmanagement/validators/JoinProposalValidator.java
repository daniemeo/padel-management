package it.solvingteam.padelmanagement.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.service.JoinProposalService;

@Component
public class JoinProposalValidator implements Validator{
	@Autowired
	JoinProposalService joinProposalService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertJoinProposalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		InsertJoinProposalDto insertJoinProposalDto = (InsertJoinProposalDto)target;
		for(JoinProposal joinProposal : joinProposalService.findJoinProposalByUser(insertJoinProposalDto.getUserDto().getId())) {
		if(joinProposal.getProposalStatus().equals(ProposalStatus.PENDING)) {
			errors.rejectValue("proposalStatus", "proposalError", "Hai già presentato una proposta");
		}
		if(joinProposal.getProposalStatus().equals(ProposalStatus.APPROVED)) {
			errors.rejectValue("proposalStatus", "proposalError", "Sei già in un circolo");
		}

	}
	}

}
