package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GameDto {
	@NotNull
	private String id;
	private String date;
	private Boolean payed;
	private String missingPlayers;
	
	@Valid
	private CourtDto courtDto;
	
	@Valid 
	private PlayerDto playerDto;
	
	private List<PlayerDto> playersDto;
	
	private List<String> slots;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public String getMissingPlayers() {
		return missingPlayers;
	}

	public void setMissingPlayers(String missingPlayers) {
		this.missingPlayers = missingPlayers;
	}

	public CourtDto getCourtDto() {
		return courtDto;
	}

	public void setCourtDto(CourtDto courtDto) {
		this.courtDto = courtDto;
	}

	public PlayerDto getPlayerDto() {
		return playerDto;
	}

	public void setPlayerDto(PlayerDto playerDto) {
		this.playerDto = playerDto;
	}

	public List<PlayerDto> getPlayersDto() {
		return playersDto;
	}

	public void setPlayersDto(List<PlayerDto> playersDto) {
		this.playersDto = playersDto;
	}
	

	
	public List<String> getSlots() {
		return slots;
	}

	public void setSlots(List<String> slots) {
		this.slots = slots;
	}

	@Override
	public String toString() {
		return "date=" + date + ", payed=" + payed + ", missingPlayers=" + missingPlayers ;
	}
	
	

}
