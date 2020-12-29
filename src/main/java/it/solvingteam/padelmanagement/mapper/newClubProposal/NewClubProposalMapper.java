package it.solvingteam.padelmanagement.mapper.newClubProposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;

@Component
public class NewClubProposalMapper extends AbstractMapper<NewClubProposal, NewClubProposalDto>{
	
@Autowired
UserMapper userMapper;

	@Override
	public NewClubProposalDto convertEntityToDto(NewClubProposal entity) {
		if (entity == null) {
			return null;
		}
		NewClubProposalDto dto = new NewClubProposalDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setCity(entity.getCity());
		dto.setAddress(entity.getAddress());
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setLogo(entity.getLogo());
		dto.setUserDto(userMapper.convertEntityToDto(entity.getCreator()));
		return dto;
	}

	@Override
	public NewClubProposal convertDtoToEntity(NewClubProposalDto dto) {
		if (dto == null) {
			return null;
		}
		NewClubProposal newClubProposal = new NewClubProposal();
		if(dto.getId()!= null) {
		   newClubProposal.setId(Long.parseLong(dto.getId()));
		}
		newClubProposal.setName(dto.getName());
		newClubProposal.setCity(dto.getCity());
		newClubProposal.setAddress(dto.getAddress());
		newClubProposal.setProposalStatus(Enum.valueOf(ProposalStatus.class,dto.getProposalStatus()));
		newClubProposal.setLogo(dto.getLogo());
		newClubProposal.setCreator(userMapper.convertDtoToEntity(dto.getUserDto()));
		return newClubProposal;
	}
   
	public NewClubProposal convertDtoToEntityInsert(InsertNewClubProposalDto dto) {
		if (dto == null) {
			return null;
		}
		NewClubProposal newClubProposal = new NewClubProposal();
		
		newClubProposal.setName(dto.getName());
		newClubProposal.setCity(dto.getCity());
		newClubProposal.setAddress(dto.getAddress());
		newClubProposal.setLogo(dto.getLogo());
		newClubProposal.setCreator(userMapper.convertDtoToEntity(dto.getUserDto()));
		return newClubProposal;
	}
	public InsertNewClubProposalDto convertEntityToDtoInsert(NewClubProposal entity) {
		if (entity == null) {
			return null;
		}
		InsertNewClubProposalDto dto = new InsertNewClubProposalDto();
		dto.setName(entity.getName());
		dto.setCity(entity.getCity());
		dto.setAddress(entity.getAddress());
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setLogo(entity.getLogo());
		dto.setUserDto(userMapper.convertEntityToDto(entity.getCreator()));
		return dto;
	}
	
}
