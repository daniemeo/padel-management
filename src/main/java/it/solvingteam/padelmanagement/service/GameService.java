package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.dto.message.game.GameUpdateMissingPlayersDto;
import it.solvingteam.padelmanagement.dto.message.game.UpdateGameDto;
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

	@Autowired
	EmailService emailService;

	public List<GameDto> findAll(Long idPlayer) throws Exception {
		List<GameDto> games = gameMapper.convertEntityToDto(gameRepository.findAllGamesByPlayer_Id(idPlayer));
		return games;
	}

	public Game getGame(@NotNull String id) {
		return this.gameRepository.findById(Long.parseLong(id)).orElse(null);
	}

	public List<GameCheckDto> update(UpdateGameDto updateGameDto) throws Exception {
		this.delete(updateGameDto.getGameId());
		return this.check(updateGameDto.getGameCheckDto());
	}

	public GameDto updateMissingPlayers(GameUpdateMissingPlayersDto gameUpdateMissingPlayersDto) throws Exception {
		Game game = this.getGame(gameUpdateMissingPlayersDto.getGameId());
		Integer otherPlayers = game.getPlayers().size();
		Integer maxPlayers = 3;
		maxPlayers = 3 - otherPlayers;
		Integer missingPlayers = Integer.parseInt(gameUpdateMissingPlayersDto.getMissingPlayers());
		if (missingPlayers <= maxPlayers) {
			game.setMissingPlayers(missingPlayers);
			game = gameRepository.save(game);
			if (game.getMissingPlayers() == 0) {
				emailService.sendMail(game.getPlayer().getUser().getMailAddress(), "partita confermata",
						"la tua partita è stata confermata" + game);
				for (Player player : game.getPlayers()) {
					emailService.sendMail(player.getUser().getMailAddress(), "partita confermata",
							"la tua partita è stata confermata" + game + "/n");
				}

			}
			return gameMapper.convertEntityToDto(game);
		} else {
			throw new Exception("numero di giocatori mancanti : " + " " + maxPlayers);

		}

	}

	public SuccessMessageDto delete(String id) throws Exception {
		Game game = this.getGame(id);
		if (game.getDate().isBefore(LocalDate.now().minusDays(1))) {
			throw new Exception("imposssibile eliminare una partita terminata");
		}
		if (game.getDate().equals(LocalDate.now()) && LocalTime
				.of(game.getSlots().iterator().next().getHour(), game.getSlots().iterator().next().getMinute())
				.isBefore(LocalTime.now().plusMinutes(30))) {
			throw new Exception("impossibile eliminare/modificare una partita terminata o che sta per iniziare");

		}
		gameRepository.delete(game);
		SuccessMessageDto successDto = new SuccessMessageDto("partita eliminata con successo");
		return successDto;

	}

	public List<GameCheckDto> check(GameCheckDto gameCheckDto) throws Exception {

		Player player = playerService.getPlayerClub(gameCheckDto.getPlayerId());
		List<Game> games = gameRepository.listAllGamesSearch(LocalDate.parse(gameCheckDto.getDate()),
				player.getClub().getId());
		Set<CourtDto> allCourtsAvailable = new HashSet<>();
		List<GameCheckDto> gamesAvailable = new ArrayList<>();
		List<Slot> slotInsert = new ArrayList<Slot>();

		for (String slot : gameCheckDto.getSlots()) {
			Slot slotDb = slotRepository.findById(Long.parseLong(slot)).get();
			slotInsert.add(slotDb);
		}

		Set<CourtDto> allCourts = courtService.findAll(String.valueOf(player.getClub().getAdmin().getId())).stream()
				.collect(Collectors.toSet());

		Boolean trovato = false;
		// se la lista di partite è vuota, torna la lista di tutti i campi
		if (games.isEmpty()) {

			for (CourtDto court : allCourts) {
				if (court.getIsInactive() == false) {
					allCourtsAvailable.add(court);
				}
			}
			for (CourtDto courtDto : allCourtsAvailable) {
				GameCheckDto gameCheck = new GameCheckDto();
				gameCheck.setDate(gameCheckDto.getDate());
				gameCheck.setPlayerId(gameCheckDto.getPlayerId());
				gameCheck.setSlots(gameCheckDto.getSlots());
				gameCheck.setCourtDto(courtDto);
				gamesAvailable.add(gameCheck);
			}
			return gamesAvailable;

		}

		// lista di campi non disponibili
		List<CourtDto> courtsNotAvailable = new ArrayList<CourtDto>();

		for (Game game : games) {
			CourtDto court = courtService.FindCourtByGame(game.getId());
			for (Slot slotCheck : slotInsert) {
				for (Slot slotGame : game.getSlots()) {
					trovato = slotGame.equals(slotCheck);
					if (trovato) {
						courtsNotAvailable.add(court);
					}
				}

			}
		}

		if (courtsNotAvailable.isEmpty()) {

			for (CourtDto court : allCourts) {
				if (court.getIsInactive() == false) {
					allCourtsAvailable.add(court);
				}
			}
			for (CourtDto courtDto : allCourtsAvailable) {
				GameCheckDto gameCheck = new GameCheckDto();
				gameCheck.setDate(gameCheckDto.getDate());
				gameCheck.setPlayerId(gameCheckDto.getPlayerId());
				gameCheck.setSlots(gameCheckDto.getSlots());
				gameCheck.setCourtDto(courtDto);
				gamesAvailable.add(gameCheck);
			}
			return gamesAvailable;

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
					break;

				}
			}
			if (available) {
				allCourtsAvailable.add(courtDb);
			}
		}

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
			if (checkDto.getCourtDto().getId().equals(gameCheckDto.getCourtDto().getId())) {
				continueBooking = true;
				break;
			} else {
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

			GameDto gameDto = gameMapper.convertEntityToDto(gameRepository.save(game));
			if (game.getMissingPlayers() == 0) {
				emailService.sendMail(game.getPlayer().getUser().getMailAddress(), "partita confermata",
						"la tua partita è stata confermata" + game);
			}
			return gameDto;
		} else {
			throw new Exception("Prenotazione non disponibile");
		}
	}

	public List<GameDto> findAllCallForAction(String idPlayer) throws Exception {
		Player player = playerService.findById(idPlayer);
		List<Game> callAllForAction = gameRepository.findAllGamesByPlayer_IdNotAndDateAfterAndMissingPlayersNotAndPlayer_Club_IdEquals(
				Long.parseLong(idPlayer), LocalDate.now().minusDays(1), 0, player.getClub().getId());
		return gameMapper.convertEntityToDto(callAllForAction);

	}

	public SuccessMessageDto gameJoin(Player player, String idGame) throws Exception {
		Game game = gameRepository.findById(Long.parseLong(idGame)).get();
		if (game.getMissingPlayers() > 0) {
			game.getPlayers().add(player);
			Integer players = game.getMissingPlayers();
			Integer missingPlayers = players - 1;
			game.setMissingPlayers(missingPlayers);
			gameRepository.save(game);
			if (game.getMissingPlayers() == 0) {
				emailService.sendMail(game.getPlayer().getUser().getMailAddress(), "partita confermata",
						"la tua partita è stata confermata" + game);
				for (Player player1 : game.getPlayers()) {
					emailService.sendMail(player1.getUser().getMailAddress(), "partita confermata",
							"la tua partita è stata confermata" + game + "/n");
				}

			}
			SuccessMessageDto successDto = new SuccessMessageDto("prenotazione effettuata!! Complimenti");
			return successDto;
		} else {
			throw new Exception("Mi dispiace ma la partia a cui cercavi di iscriverti è già stata completata!");
		}

	}
}
