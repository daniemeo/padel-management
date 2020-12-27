package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CourtDto {
	@NotNull
	private String id;
	private String name;
	private Boolean isInactive;
	private String price;
	
	@Valid 
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
		return "name=" + name + ", isInactive=" + isInactive + ", price=" + price;
	}
	
	
	

}
