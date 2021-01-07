package it.solvingteam.padelmanagement.dto.message.notice;

import javax.validation.constraints.NotBlank;


public class InsertNoticeDto {
	@NotBlank
	private String message;
	private String creationDate;
	
	
	private String adminId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	
	
}
