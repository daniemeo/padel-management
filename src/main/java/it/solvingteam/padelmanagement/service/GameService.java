package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.game.GameMapper;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.slot.Slot;
import it.solvingteam.padelmanagement.repository.GameRepository;
import it.solvingteam.padelmanagement.repository.SlotRepository;

@Service
public class GameService {
	@Autowired
	PlayerService playerService;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	CourtMapper courtMapper;

	@Autowired
	CourtService courtService;

	@Autowired
	GameMapper gameMapper;

	public List<GameCheckDto> check(GameCheckDto gameCheckDto) throws Exception {
		
		Player player = playerService.getPlayerClub(gameCheckDto.getPlayerId());
		List<Game> games = gameRepository.listAllGamesSearch(LocalDate.parse(gameCheckDto.getDate()),
				player.getClub().getId());
		Set<CourtDto> allCourtsAvailable = new HashSet<>();

		if (games.isEmpty()) {
			Set<CourtDto> allCourts = courtService.findAll(String.valueOf(player.getClub().getAdmin().getId())).stream()
					.collect(Collectors.toSet());
			for (CourtDto court : allCourts) {
				if (court.getIsInactive() == false) {
					allCourtsAvailable.add(court);
				}
			}
		}
		List<Slot> slotInsert = new ArrayList<Slot>();
		for (String slot : gameCheckDto.getSlots()) {
			Slot slotDb = slotRepository.findById(Long.parseLong(slot)).get();
			slotInsert.add(slotDb);
		}
		// lista di campi non disponibili
		List<CourtDto> courtsNotAvailable = new ArrayList<CourtDto>();

		Boolean trovato = false;
		for (Game game : games) {
			CourtDto court = courtMapper.convertEntityToDto(game.getCourt());
			for (Slot slotCheck : slotInsert) {
				for (Slot slotGame : game.getSlots()) {
					trovato = slotGame.equals(slotCheck);
					if (trovato) {
						courtsNotAvailable.add(court);
					}
				}

			}
		}
		// facciamo un set di campi disponibili
		Boolean available = false;
		// list all dei campi
		for (CourtDto courtDb : courtService.findAll(String.valueOf(player.getClub().getAdmin().getId()))) {
			// lista all campi non disponibili
			for (CourtDto courtNotAvailableDto : courtsNotAvailable) {
				if (!courtDb.getId().equals(courtNotAvailableDto.getId()) && courtDb.getIsInactive() == false) {
					available = true;
				} else {
					available = false;
				}
			}
			if (available) {
				allCourtsAvailable.add(courtDb);
			}
		}

		List<GameCheckDto> gamesAvailable = new ArrayList<>();
		for (CourtDto court : allCourtsAvailable) {
			GameCheckDto gameCheck = new GameCheckDto();
			gameCheck.setDate(gameCheckDto.getDate());
			gameCheck.setPlayerId(gameCheckDto.getPlayerId());
			gameCheck.setSlots(gameCheckDto.getSlots());
			gameCheck.setCourtDto(court);
			gamesAvailable.add(gameCheck);

		}
		return gamesAvailable;

	}

	public GameDto insert(GameCheckDto gameCheckDto) throws Exception {
		 Boolean continueBooking = false;
		for (GameCheckDto checkDto : this.check(gameCheckDto)) {
			if (!checkDto.equals(gameCheckDto)) {
				continueBooking = true;
			}
			else {
				continueBooking = false;
			}
		}
		if (continueBooking) {
			Player player = playerService.findById(gameCheckDto.getPlayerId());
			List<Slot> slotInsert = new ArrayList<Slot>();
			for (String slot : gameCheckDto.getSlots()) {
				Slot slotDaDb = slotRepository.findById(Long.parseLong(slot)).get();
				slotInsert.add(slotDaDb);
			}

			Court court = courtMapper.convertDtoToEntity(gameCheckDto.getCourtDto());
			Game game = new Game();
			game.setCourt(court);
			game.setDate(LocalDate.parse(gameCheckDto.getDate()));
			game.setMissingPlayers(Integer.parseInt(gameCheckDto.getMissingPlayers()));
			game.setPlayer(player);
			game.setSlots(slotInsert);
			game.setPayed(false);
			return gameMapper.convertEntityToDto(gameRepository.save(game));
		} else {
			throw new Exception("Prenotazione non disponibile");
		}
	}

}
