package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.repository.JoinProposalRepository;

@Service
public class JoinProposalService {
@Autowired 
JoinProposalRepository joinProposalRepository;

@Autowired 
JoinProposalMapper joinProposalMapper;

@Autowired 
GetClubMapper getClubMapper;
@Autowired
ClubService clubService;


	public List<JoinProposal>findJoinProposalByUser(String idUser) {
		return this.joinProposalRepository.findAllJoinProposalByUser_Id(Long.parseLong(idUser));
	}
	
	public JoinProposalDto insertJoinProposal(InsertJoinProposalDto insertJoinProposalDto) {
		JoinProposal joinProposal = joinProposalMapper.convertDtoToEntityInsert(insertJoinProposalDto);
		joinProposal.setClub(clubService.findById(Long.parseLong(insertJoinProposalDto.getClubDtoForJoinProposal().getId())));
		joinProposal.setProposalStatus(ProposalStatus.PENDING);
		joinProposal= this.joinProposalRepository.save(joinProposal);
		 return joinProposalMapper.convertEntityToDto(joinProposal);
	}
	
	public List<JoinProposalDto> findAll() {
		
		List<JoinProposal> joinProposal = this.joinProposalRepository.findAll();
		return joinProposalMapper.convertEntityToDto(joinProposal);
	}
}
