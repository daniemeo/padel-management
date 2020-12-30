package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.mapper.club.GetClubMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.repository.CourtRepository;

@Service
public class CourtService {
	@Autowired
	private CourtRepository courtRepository;

	@Autowired
	private ClubService clubService;

	@Autowired
	private CourtMapper courtMapper;

	@Autowired
	GetClubMapper getClubMapper;

	public CourtDto insert(InsertCourtDto insertCourtDto) {
		Club club = clubService.findClubByAdmin(Long.parseLong(insertCourtDto.getAdminId()));
		Court court = courtMapper.convertDtoToEntity(insertCourtDto);
		court.setClub(club);
		court = this.courtRepository.save(court);
		return courtMapper.convertEntityToDto(court);
	}

	public CourtDto findById(String id) {
		return courtMapper.convertEntityToDto(this.courtRepository.findById(Long.parseLong(id)).get());

	}

	public Court findByIdCourt(String id) {
		return this.courtRepository.findById(Long.parseLong(id)).get();
	}

	public List<CourtDto> findAll(String idAdmin) {
		Club club = clubService.findClubByAdmin(Long.parseLong(idAdmin));
		List<CourtDto> courts = courtMapper.convertEntityToDto(courtRepository.findAllCourtByClub_id(club.getId()));
		return courts;
	}

	public CourtDto update(CourtDto courtDto) throws Exception {
		Court court = courtRepository.findById(Long.parseLong(courtDto.getId())).get();
		if (court.getId() == null) {
			throw new Exception("id non esiste");
		}
		Court courtNew = courtMapper.convertDtoToEntity(courtDto);
		Club club = clubService.findById(court.getClub().getId());
		courtNew.setClub(club);
		return courtMapper.convertEntityToDto(courtRepository.save(courtNew));
	}

	public SuccessMessageDto setStatus(String id) throws Exception {

		if (id == null || !StringUtils.isNumeric(id)) {
			throw new Exception("id non valido");
		}
		Court courtDb = courtRepository.findById(Long.parseLong(id)).get();
		if (courtDb == null) {
			throw new Exception("id inesistente");
		}

		for (Game game : courtDb.getGames()) {
			if (game.getDate().isAfter(LocalDate.now())) {
				throw new Exception("campo con prenotazioni attive!");
			}
		}

		if (!courtDb.getIsInactive()) {
			courtDb.setIsInactive(true);
			courtRepository.save(courtDb);
		} else {
			courtDb.setIsInactive(false);
			courtRepository.save(courtDb);
		}
		SuccessMessageDto successDto = new SuccessMessageDto("cambio stato campo effettuato con successo");
		return successDto;

	}

}
