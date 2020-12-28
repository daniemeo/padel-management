package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.NewClubProposalService;
import it.solvingteam.padelmanagement.validators.NewClubProposalValidator;

@RestController
@RequestMapping("newClubProposal")
public class NewClubProposalController {
	
	 @Autowired 
	 NewClubProposalService newClubProposalService;
	 
	 @Autowired 
	 NewClubProposalValidator newClubProposalValidator;
	 
	 @GetMapping("listAll")
		public ResponseEntity<List<NewClubProposalDto>> list() {
	      List<NewClubProposalDto> newClubProposal = newClubProposalService.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(newClubProposal);	
		}
	 
	@PostMapping("insertNewClubProposal")
	 public ResponseEntity<InsertNewClubProposalDto> insertNewClubProposal(@Valid @RequestBody InsertNewClubProposalDto insertnewClubProposalDto, 
			 BindingResult bindingResult) throws Exception {
		 newClubProposalValidator.validate(insertnewClubProposalDto,bindingResult);
		 if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
		 }
		 insertnewClubProposalDto = newClubProposalService.insertNewClubProposal(insertnewClubProposalDto);
		 return ResponseEntity.status(HttpStatus.OK).body(insertnewClubProposalDto);
	}
	
}