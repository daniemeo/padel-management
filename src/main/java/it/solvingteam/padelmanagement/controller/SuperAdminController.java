package it.solvingteam.padelmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.service.NewClubProposalService;

@RestController
@RequestMapping("superAdmin")
public class SuperAdminController {

	 @Autowired
	 NewClubProposalService newClubProposalService;
	 
	 @GetMapping("approve/{id}")
		public ResponseEntity<SuccessMessageDto>approveProposal(@PathVariable("id") String id) throws Exception {
			newClubProposalService.clubApproval(id);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("club approvato!"));
		}
	 
	 @GetMapping("reject/{id}")
		public ResponseEntity<SuccessMessageDto>rejectProposal(@PathVariable("id") String id) throws Exception {
			newClubProposalService.clubReject(id);
			return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("club rifiutato!"));
		}
	 
	 
	 
}
