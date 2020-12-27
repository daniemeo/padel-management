package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ClubDto {
	@NotNull
	private String id;
	private String city;
	private String name;
	private String address;
	private Byte[] logo;

	@Valid
	private List<JoinProposalDto> joinProposalDto;
	@Valid
	private List<NoticeDto> noticesDto;
	@Valid
	private List<PlayerDto> playersDto;
	@Valid
	private List<CourtDto> courtsDto;
	@Valid
	private AdminDto adminDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<JoinProposalDto> getJoinProposalDto() {
		return joinProposalDto;
	}

	public void setJoinProposalDto(List<JoinProposalDto> joinProposalDto) {
		this.joinProposalDto = joinProposalDto;
	}

	public List<NoticeDto> getNoticesDto() {
		return noticesDto;
	}

	public void setNoticesDto(List<NoticeDto> noticesDto) {
		this.noticesDto = noticesDto;
	}

	public List<PlayerDto> getPlayersDto() {
		return playersDto;
	}

	public void setPlayersDto(List<PlayerDto> playersDto) {
		this.playersDto = playersDto;
	}

	public List<CourtDto> getCourtsDto() {
		return courtsDto;
	}

	public void setCourtsDto(List<CourtDto> courtsDto) {
		this.courtsDto = courtsDto;
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	@Override
	public String toString() {
		return "city=" + city + ", name=" + name;
	}

}
