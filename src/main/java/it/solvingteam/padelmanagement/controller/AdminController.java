package it.solvingteam.padelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.service.JoinProposalService;

@RestController
@RequestMapping("adminController")
public class AdminController {
	@Autowired
	JoinProposalService joinProposalService;

	@GetMapping("approve/{id}")
	public ResponseEntity<SuccessMessageDto> approveJoinProposal(@PathVariable("id") String id) throws Exception {
		joinProposalService.joinProposalApproval(id);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("richiesta approvata!"));
	}

	@GetMapping("reject/{id}")
	public ResponseEntity<SuccessMessageDto> rejectJoinProposal(@PathVariable("id") String id) throws Exception {
		joinProposalService.joinProposalReject(id);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("richiesta rifiutata!"));
	}
}
