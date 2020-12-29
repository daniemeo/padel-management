package it.solvingteam.padelmanagement.mapper.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.admin.AdminMapper;
import it.solvingteam.padelmanagement.model.club.Club;
@Component
public class GetClubMapper  extends AbstractMapper<Club, ClubDto>{
	@Autowired
	AdminMapper adminMapper;

	@Override
	public ClubDto convertEntityToDto(Club entity) {
		if (entity == null) {
			return null;
		}
		ClubDto dto = new ClubDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setCity(entity.getCity());
		dto.setName(entity.getName());
		dto.setAddress(entity.getAddress());
		dto.setLogo(entity.getLogo());
		dto.setAdminDto(adminMapper.convertEntityToDto(entity.getAdmin()));
		return dto;
	}

	@Override
	public Club convertDtoToEntity(ClubDto dto) {
		if (dto == null) {
			return null;
		}

		Club club = new Club();
		if(dto.getId()!= null) {
			club.setId(Long.parseLong(dto.getId()));
		}
		club.setCity(dto.getCity());
		club.setName(dto.getName());
		club.setAddress(dto.getAddress());
		club.setLogo(dto.getLogo());
		club.setAdmin(adminMapper.convertDtoToEntity(dto.getAdminDto()));
		return club;

	}
}
