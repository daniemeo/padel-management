package it.solvingteam.padelmanagement.service;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.mapper.newClubProposal.NewClubProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.NewClubProposalRepository;

@Service
public class NewClubProposalService {
@Autowired
NewClubProposalRepository newClubProposalRepository;

@Autowired
NewClubProposalMapper newClubProposalMapper;

@Autowired
UserService userService;

@Autowired
AdminService adminService;

@Autowired
ClubService clubService;

@Autowired
EmailService emailService;

public InsertNewClubProposalDto insertNewClubProposal(InsertNewClubProposalDto insertNewClubProposalDto) {
	NewClubProposal newClubProposal = newClubProposalMapper.convertDtoToEntityInsert(insertNewClubProposalDto);
	 newClubProposal.setProposalStatus(ProposalStatus.PENDING);
	 newClubProposal= this.newClubProposalRepository.save(newClubProposal);
	 return newClubProposalMapper.convertEntityToDtoInsert(newClubProposal);
}

public List<NewClubProposal>findNewProposalByUser(String idUser) {
	return this.newClubProposalRepository.findAllNewClubProposalByCreator_Id(Long.parseLong(idUser));
}

public List<NewClubProposalDto> findAll() {
	
	List<NewClubProposal> newClubProposal = this.newClubProposalRepository.findAll();
	return newClubProposalMapper.convertEntityToDto(newClubProposal);
	
}
public NewClubProposal update(NewClubProposal newClubProposal) {
	newClubProposal = this.newClubProposalRepository.save(newClubProposal);
	return newClubProposal;
}


public void clubApproval(String id) throws Exception{
	if(id == null || !StringUtils.isNumeric(id) ) {
		throw new Exception("id non esiste");
	}
	NewClubProposal newCLubProposal = newClubProposalRepository.findById(Long.parseLong(id)).get();
	if( newCLubProposal == null ) {
		throw new Exception("id non esiste");
	}
	
	if(!(newCLubProposal.getProposalStatus() == ProposalStatus.PENDING) ){
		throw new Exception("proposta già presentata");
	}
	newCLubProposal.setProposalStatus(ProposalStatus.APPROVED);
	this.update(newCLubProposal);
	User user = userService.findById(newCLubProposal.getCreator().getId());
    Admin admin = adminService.insertAdmin(user);
	Club club = new Club(newCLubProposal.getCity(),newCLubProposal.getName(),newCLubProposal.getAddress(),admin);
    club = clubService.insertClub(club);
    emailService.sendMail(user.getMailAddress(),"club approvato" ,"il tuo club" +" " +club.getName()+" " + "è stato approvato");
}

public void clubReject(String id) throws Exception{
	if(id == null || !StringUtils.isNumeric(id) ) {
		throw new Exception("id non esiste");
	}
	
	NewClubProposal newCLubProposal = newClubProposalRepository.findById(Long.parseLong(id)).get();
	if( newCLubProposal == null ) {
		throw new Exception("id non esiste");
	}
	if(!(newCLubProposal.getProposalStatus() == ProposalStatus.PENDING) ){
		throw new Exception("proposta già presentata");
	}
	newCLubProposal.setProposalStatus(ProposalStatus.REJECTED);
	this.update(newCLubProposal);
	User user = userService.findById(newCLubProposal.getCreator().getId());
	
	emailService.sendMail(user.getMailAddress(), "club non approvato", "la tua proposta non è stata approvata");
}
}
