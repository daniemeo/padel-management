package it.solvingteam.padelmanagement.dto.message.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertUserDto {
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotBlank
	private String dateOfBirth;
	
	@Email(regexp = ".+@.+\\.[a-z]+")
	private String mailAddress;
	
	@NotBlank
	@Size(message = "il campo mobile deve contenere anche il prefisso",max = 13, min = 13)
	private String mobile;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
    private String repeatePassword;
	private Byte[] profilePic;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(Byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public String getRepeatePassword() {
		return repeatePassword;
	}
	public void setRepeatePassword(String repeatePassword) {
		this.repeatePassword = repeatePassword;
	}
	
	
}
