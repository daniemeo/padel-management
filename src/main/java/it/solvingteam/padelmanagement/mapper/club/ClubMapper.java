package it.solvingteam.padelmanagement.mapper.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.admin.AdminMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.mapper.notice.NoticeMapper;
import it.solvingteam.padelmanagement.mapper.player.PlayerMapper;
import it.solvingteam.padelmanagement.model.club.Club;

@Component
public class ClubMapper extends AbstractMapper<Club, ClubDto> {
	@Autowired

	JoinProposalMapper joinProposalMapper;

	@Autowired
	NoticeMapper noticeMapper;

	@Autowired
	PlayerMapper playerMapper;

	@Autowired
	CourtMapper courtMapper;

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
		dto.setJoinProposalDto(joinProposalMapper.convertEntityToDto(entity.getJoinProposals()));
		dto.setNoticesDto(noticeMapper.convertEntityToDto(entity.getNotices()));
		dto.setPlayersDto(playerMapper.convertEntityToDto(entity.getPlayers()));
		dto.setCourtsDto(courtMapper.convertEntityToDto(entity.getCourts()));
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
		club.setJoinProposals(joinProposalMapper.convertDtoToEntity(dto.getJoinProposalDto()));
		club.setNotices(noticeMapper.convertDtoToEntity(dto.getNoticesDto()));
		club.setPlayers(playerMapper.convertDtoToEntity(dto.getPlayersDto()));
		club.setCourts(courtMapper.convertDtoToEntity(dto.getCourtsDto()));
		club.setAdmin(adminMapper.convertDtoToEntity(dto.getAdminDto()));
		return club;

	}

}
