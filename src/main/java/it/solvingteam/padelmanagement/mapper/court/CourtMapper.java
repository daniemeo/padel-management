package it.solvingteam.padelmanagement.mapper.court;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.game.GameMapper;
import it.solvingteam.padelmanagement.model.court.Court;


@Component
public class CourtMapper  extends AbstractMapper<Court, CourtDto>{
@Autowired
ClubMapper clubMapper;

@Autowired 
GameMapper gameMapper;

@Autowired 
GetClubMapper getClubMapper;

	
//	public CourtDto convertEntityToDto(Court entity) {
//		if (entity == null) {
//			return null;
//		}
//		CourtDto dto = new CourtDto();
//		dto.setId(String.valueOf(entity.getId()));
//		dto.setName(entity.getName());
//		dto.setIsInactive(entity.getIsInactive());
//		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));
//		dto.setGameDto(gameMapper.convertEntityToDto(entity.getGames()));
//		return dto;
//		
//	}

	@Override
	public Court convertDtoToEntity(CourtDto dto) {
		if (dto == null) {
			return null;
		}
		Court court = new Court();
		if(dto.getId()!= null) {
		   court.setId(Long.parseLong(dto.getId()));
		}
		court.setName(dto.getName());
		court.setIsInactive(dto.getIsInactive());
		court.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));
		return court;
	}
	
	public Court convertDtoToEntity(InsertCourtDto dto) {
		if (dto == null) {
			return null;
		}
		
		Court court = new Court();
	
		court.setName(dto.getName());
		court.setIsInactive(dto.getIsInactive());
		
		return court;
	}
	@Override
	public CourtDto convertEntityToDto(Court entity) {
		if (entity == null) {
			return null;
		}
		CourtDto dto = new CourtDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setIsInactive(entity.getIsInactive());
		dto.setClubDto(getClubMapper.convertEntityToDto(entity.getClub()));
		return dto;
		
	}

	
	
	
	@Override
	 public List<CourtDto> convertEntityToDto(List<Court> entities) {
	        if (entities == null) {
	            return null;
	        }

	        List<CourtDto> dtos = new ArrayList<>();

	        for (Court entity : entities) {
	            dtos.add(convertEntityToDto(entity));
	        }

	        return dtos;
	    }
	
	@Override
    public List<Court> convertDtoToEntity(List<CourtDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Court> entities = new ArrayList<>();

        for (CourtDto dto : dtos) {
            entities.add(convertDtoToEntity(dto));
        }

        return entities;
    }
}
