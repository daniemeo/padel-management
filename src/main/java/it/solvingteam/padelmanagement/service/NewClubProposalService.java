package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.mapper.newClubProposal.NewClubProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.repository.NewClubProposalRepository;

@Service
public class NewClubProposalService {
@Autowired
NewClubProposalRepository newClubProposalRepository;

@Autowired
NewClubProposalMapper newClubProposalMapper;

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
}
