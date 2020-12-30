package it.solvingteam.padelmanagement.dto.message.joinProposal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.club.ClubDtoId;

public class InsertJoinProposalDto {
	 @NotBlank
	 private String userLevel;
	 private String proposalStatus;
	 @Valid
	 private UserDto userDto;
	 @Valid
	 private  ClubDtoId  clubDtoId;
	 
	 public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public ClubDtoId getClubDtoId() {
		return clubDtoId;
	}
	public void setClubDtoId(ClubDtoId clubDtoId) {
		this.clubDtoId = clubDtoId;
	}
	

	 
	 
}
