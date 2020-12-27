package it.solvingteam.padelmanagement.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AdminDto {
	@NotNull
	private String id;
	
	@Valid 
	private UserDto userDto;
	
	@Valid 
	private ClubDto clubDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public ClubDto getClubDto() {
		return clubDto;
	}

	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}
	
	
}
