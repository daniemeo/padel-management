package it.solvingteam.padelmanagement.mapper.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.player.PlayerMapper;
import it.solvingteam.padelmanagement.model.game.Game;

@Component
public class GameMapper extends AbstractMapper<Game, GameDto>{
@Autowired
CourtMapper courtMapper;

@Autowired
PlayerMapper playerMapper;



	@Override
	public GameDto convertEntityToDto(Game entity) {
		if (entity == null) {
			return null;
		}
		
		GameDto dto = new GameDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setDate(String.valueOf(entity.getDate()));
		dto.setPayed(entity.getPayed());
		dto.setMissingPlayers(String.valueOf(entity.getMissingPlayers()));
		dto.setCourtDto(courtMapper.convertEntityToDto(entity.getCourt()));
		dto.setPlayerDto(playerMapper.convertEntityToDto(entity.getPlayer()));
		dto.setPlayersDto(playerMapper.convertEntityToDto(entity.getPlayers()));
		return dto;
		
	}

	@Override
	public Game convertDtoToEntity(GameDto dto) {
		if (dto == null) {
			return null;
		}
		Game game = new Game();
		game.setId(Long.parseLong(dto.getId()));
		game.setDate(LocalDate.parse(dto.getDate()));
		game.setPayed(dto.getPayed());
		game.setMissingPlayers(Integer.parseInt(dto.getMissingPlayers()));
		game.setCourt(courtMapper.convertDtoToEntity(dto.getCourtDto()));
		game.setPlayer(playerMapper.convertDtoToEntity(dto.getPlayerDto()));
		game.setPlayers(playerMapper.convertDtoToEntity(dto.getPlayersDto()));
		return game;
	}
	@Override
	 public List<GameDto> convertEntityToDto(List<Game> entities) {
	        if (entities == null) {
	            return null;
	        }

	        List<GameDto> dtos = new ArrayList<>();

	        for (Game entity : entities) {
	            dtos.add(convertEntityToDto(entity));
	        }

	        return dtos;
	    }
	@Override
   public List<Game> convertDtoToEntity(List<GameDto> dtos) {
       if (dtos == null) {
           return null;
       }

       List<Game> entities = new ArrayList<>();

       for (GameDto dto : dtos) {
           entities.add(convertDtoToEntity(dto));
       }

       return entities;
   }

}
