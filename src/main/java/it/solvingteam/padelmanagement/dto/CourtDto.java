package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourtDto {
	@NotNull
	private String id;
	@NotBlank
	private String name;
	
	private Boolean isInactive;
    
	private ClubDto clubDto;
	
	private List<GameDto> gameDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsInactive() {
		return isInactive;
	}

	public void setIsInactive(Boolean isInactive) {
		this.isInactive = isInactive;
	}


	public ClubDto getClubDto() {
		return clubDto;
	}

	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}

	public List<GameDto> getGameDto() {
		return gameDto;
	}

	public void setGameDto(List<GameDto> gameDto) {
		this.gameDto = gameDto;
	}

	@Override
	public String toString() {
		return "name=" + name + ", isInactive=" + isInactive ;
	}
	
	
	

}
