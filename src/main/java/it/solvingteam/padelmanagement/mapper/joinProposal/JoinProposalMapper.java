package it.solvingteam.padelmanagement.mapper.joinProposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

@Component
public class JoinProposalMapper extends AbstractMapper<JoinProposal, JoinProposalDto> {
	@Autowired
	UserMapper userMapper;

	@Autowired
	ClubMapper clubMapper;
     
	@Autowired 
	GetClubMapper getClubMapper;
	
	@Override
	public JoinProposalDto convertEntityToDto(JoinProposal entity) {
		if (entity == null) {
			return null;
		}
		JoinProposalDto dto = new JoinProposalDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setUserLevel(String.valueOf(entity.getUserLevel()));
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		dto.setClubDto(getClubMapper.convertEntityToDto(entity.getClub()));
		return dto;

	}

	@Override
	public JoinProposal convertDtoToEntity(JoinProposalDto dto) {
		if (dto == null) {
			return null;
		}
		JoinProposal joinProposal = new JoinProposal();
		if(dto.getId()!= null) {
		   joinProposal.setId(Long.parseLong(dto.getId()));
		}
		joinProposal.setUserLevel(Integer.valueOf(dto.getUserLevel()));
		joinProposal.setProposalStatus(Enum.valueOf(ProposalStatus.class,dto.getProposalStatus()));
		joinProposal.setUser(userMapper.convertDtoToEntity(dto.getUserDto()));
		joinProposal.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));

		return joinProposal;
	}
	
	public InsertJoinProposalDto convertEntityToDtoInsert(JoinProposal entity) {
		if (entity == null) {
			return null;
		}
		InsertJoinProposalDto dto = new InsertJoinProposalDto();
		dto.setUserLevel(String.valueOf(entity.getUserLevel()));
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		
		return dto;

	}
	public JoinProposal convertDtoToEntityInsert(InsertJoinProposalDto dto) {
		if (dto == null) {
			return null;
		}
		JoinProposal joinProposal = new JoinProposal();
		
		joinProposal.setUserLevel(Integer.valueOf(dto.getUserLevel()));
		joinProposal.setUser(userMapper.convertDtoToEntity(dto.getUserDto()));
		
		return joinProposal;
	}
    
	

	@Override
	public List<JoinProposalDto> convertEntityToDto(List<JoinProposal> entities) {
		if (entities == null) {
			return null;
		}

		List<JoinProposalDto> dtos = new ArrayList<>();

		for (JoinProposal entity : entities) {
			dtos.add(convertEntityToDto(entity));
		}

		return dtos;
	}

	@Override
	public List<JoinProposal> convertDtoToEntity(List<JoinProposalDto> dtos) {
		if (dtos == null) {
			return null;
		}

		List<JoinProposal> entities = new ArrayList<>();

		for (JoinProposalDto dto : dtos) {
			entities.add(convertDtoToEntity(dto));
		}

		return entities;
	}
}
