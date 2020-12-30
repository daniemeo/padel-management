package it.solvingteam.padelmanagement.dto.message.court;

import javax.validation.constraints.NotBlank;


public class InsertCourtDto {
	
	@NotBlank
	private String name;
	private Boolean isInactive;
    
	@NotBlank
	private String adminId;
	
	public InsertCourtDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	
}
