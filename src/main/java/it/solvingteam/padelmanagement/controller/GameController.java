package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.dto.message.game.GameUpdateMissingPlayersDto;
import it.solvingteam.padelmanagement.dto.message.game.UpdateGameDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.service.GameService;
import it.solvingteam.padelmanagement.service.PlayerService;
import it.solvingteam.padelmanagement.util.TokenDecripter;
import it.solvingteam.padelmanagement.validators.GameCheckValidator;

@RestController
@RequestMapping("game")
public class GameController {
	@Autowired 
	GameCheckValidator gameCheckValidator;
	
	@Autowired 
	GameService gameService;
	@Autowired 
	PlayerService playerService;

	
	@PostMapping("gameCheck")
	 public ResponseEntity<List<GameCheckDto>> gamesAvailability(@Valid @RequestBody GameCheckDto  gameCheckDto , 
			 BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Player player= playerService.findByUsername(username);
		gameCheckDto.setPlayerId(String.valueOf(player.getId()));
		gameCheckValidator.validate(gameCheckDto,bindingResult);
		 if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
		 }
		 List<GameCheckDto> games= gameService.check(gameCheckDto);
		 return ResponseEntity.status(HttpStatus.OK).body(games);
	}
	
	@PostMapping("gameInsert")
	public ResponseEntity<GameDto> gameInsert(@Valid @RequestBody GameCheckDto  gameCheckDto , 
			 BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Player player= playerService.findByUsername(username);
		gameCheckDto.setPlayerId(String.valueOf(player.getId()));
		gameCheckValidator.validate(gameCheckDto,bindingResult);
		 if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
		 }
		 GameDto game = gameService.insert(gameCheckDto);
		 return ResponseEntity.status(HttpStatus.OK).body(game);
	}
	
	 @GetMapping("listAll")
		public ResponseEntity<List<GameDto>> list() throws Exception {
		    String username = TokenDecripter.decripter();
			Player player = playerService.findByUsername(username);
	      List<GameDto> gameDto= gameService.findAll(player.getId());
			return ResponseEntity.status(HttpStatus.OK).body(gameDto);	
		}
	 
	 @DeleteMapping("delete/{id}")
	    public ResponseEntity<SuccessMessageDto> delete(@PathVariable String id) throws Exception{
		 
	        return ResponseEntity.status(HttpStatus.OK).body(gameService.delete(id));
		}
	 
	 @PutMapping("update")
		public ResponseEntity<List<GameCheckDto>> update(@Valid @RequestBody UpdateGameDto updateGameDto, BindingResult bindingResult) throws Exception {
			if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
			}
			List<GameCheckDto> games= gameService.update(updateGameDto);
			return ResponseEntity.status(HttpStatus.OK).body(games);
		}
	 
	 @PutMapping("update/missingPlayers")
		public ResponseEntity<GameDto> updateMissingPlayer(@Valid @RequestBody GameUpdateMissingPlayersDto gameUpdateMissingPlayersDto, BindingResult bindingResult) throws Exception {
			if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
			}
			GameDto games= gameService.updateMissingPlayers(gameUpdateMissingPlayersDto);
			return ResponseEntity.status(HttpStatus.OK).body(games);
		}
	 
		@GetMapping("listAllCallForAction")
		public ResponseEntity<List<GameDto>> listAllCallForAction() throws Exception {
			String username = TokenDecripter.decripter();
			Player player = playerService.findByUsername(username);
	      List<GameDto> gameDto= gameService.findAllCallForAction(String.valueOf(player.getId()));
			return ResponseEntity.status(HttpStatus.OK).body(gameDto);	
		}
	 
		@GetMapping("callForAction/{idGame}")
		public ResponseEntity<SuccessMessageDto> callForAction(@Valid @PathVariable String idGame) throws Exception {
			String username = TokenDecripter.decripter();
			Player player = playerService.findByUsername(username);
			   return ResponseEntity.status(HttpStatus.OK).body(gameService.gameJoin(player, idGame));
		}
}
