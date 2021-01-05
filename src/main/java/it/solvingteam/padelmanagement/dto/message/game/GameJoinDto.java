package it.solvingteam.padelmanagement.dto.message.game;

import javax.validation.constraints.NotBlank;

public class GameJoinDto {
	@NotBlank
	private String idGame;
	@NotBlank
	private String idPlayer;
	
	public String getIdGame() {
		return idGame;
	}
	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}
	public String getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	

}
