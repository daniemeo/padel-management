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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.GameService;
import it.solvingteam.padelmanagement.validators.GameCheckValidator;

@RestController
@RequestMapping("game")
public class GameController {
	@Autowired 
	GameCheckValidator gameCheckValidator;
	
	@Autowired 
	GameService gameService;

	
	@PostMapping("gameCheck")
	 public ResponseEntity<List<GameCheckDto>> gamesAvailability(@Valid @RequestBody GameCheckDto  gameCheckDto , 
			 BindingResult bindingResult) throws Exception {
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
		 if (bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
		 }
		 GameDto game = gameService.insert(gameCheckDto);
		 return ResponseEntity.status(HttpStatus.OK).body(game);
	}
	
	 @GetMapping("listAll/{playerId}")
		public ResponseEntity<List<GameDto>> list(@PathVariable Long playerId) throws Exception {
	      List<GameDto> gameDto= gameService.findAll(playerId);
			return ResponseEntity.status(HttpStatus.OK).body(gameDto);	
		}
	 
	 @DeleteMapping("delete/{id}")
	    public ResponseEntity<SuccessMessageDto> delete(@PathVariable String id) throws Exception{
		 
	        return ResponseEntity.status(HttpStatus.OK).body(gameService.delete(id));
		}

	
}
