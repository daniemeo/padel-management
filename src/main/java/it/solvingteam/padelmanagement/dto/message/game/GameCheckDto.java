package it.solvingteam.padelmanagement.dto.message.game;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.solvingteam.padelmanagement.dto.CourtDto;




public class GameCheckDto {
	@NotBlank
	private String date;
	@NotNull
	private String playerId;
	@NotNull
	private List<String> slots;
	
	private CourtDto courtDto;
	
	private String missingPlayers;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public List<String> getSlots() {
		return slots;
	}
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
	public CourtDto getCourtDto() {
		return courtDto;
	}
	public void setCourtDto(CourtDto courtDto) {
		this.courtDto = courtDto;
	}
	public String getMissingPlayers() {
		return missingPlayers;
	}
	public void setMissingPlayers(String missingPlayers) {
		this.missingPlayers = missingPlayers;
	}
	
	
	
	
	
	
}
