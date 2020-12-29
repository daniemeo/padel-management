package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.user.User;
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

@Autowired 
UserService userService;

@Autowired 
PlayerService playerService;

@Autowired
EmailService emailService;


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
	
	public JoinProposal update(JoinProposal joinProposal) {
		return this.joinProposalRepository.save(joinProposal);
	}

	
	public void joinProposalApproval(String id) throws Exception{
		if(id == null || !StringUtils.isNumeric(id) ) {
			throw new Exception("id non esiste");
		}
		JoinProposal joinProposal = joinProposalRepository.findById(Long.parseLong(id)).get();
		if( joinProposal == null ) {
			throw new Exception("id non esiste");
		}
		
		if(!(joinProposal.getProposalStatus() == ProposalStatus.PENDING) ){
			throw new Exception("proposta già gestita");
		}
		joinProposal.setProposalStatus(ProposalStatus.APPROVED);
		this.update(joinProposal);
		User user = userService.findById(joinProposal.getUser().getId());
		Club club = clubService.findById(joinProposal.getClub().getId());
		Player player = new Player(joinProposal.getUserLevel(), user, club);
		playerService.insert(player);
	    emailService.sendMail(user.getMailAddress(),"adesione circolo approvata" ,"la tua richiesta è stata spprovata" +
		" " + joinProposal +" " );
	}
	
	public void joinProposalReject(String id) throws Exception{
		if(id == null || !StringUtils.isNumeric(id) ) {
			throw new Exception("id non esiste");
		}
		
		JoinProposal joinProposal = joinProposalRepository.findById(Long.parseLong(id)).get();
		if( joinProposal == null ) {
			throw new Exception("id non esiste");
		}
		if(!(joinProposal.getProposalStatus() == ProposalStatus.PENDING) ){
			throw new Exception("proposta già gestita");
		}
		joinProposal.setProposalStatus(ProposalStatus.REJECTED);
		this.update(joinProposal);
		User user = userService.findById(joinProposal.getUser().getId());
		
		emailService.sendMail(user.getMailAddress(), "club non approvato", "la tua proposta non è stata approvata");
	}
}
