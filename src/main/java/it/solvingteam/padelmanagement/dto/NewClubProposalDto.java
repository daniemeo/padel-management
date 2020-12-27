package it.solvingteam.padelmanagement.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class NewClubProposalDto {
	@NotNull
	private String id;
	private String name;
	private String city;
	private String address;
	private Byte[] logo;
	
	@Valid 
	private UserDto userDto;
	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Byte[] getLogo() {
		return logo;
	}
	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	
}
