package myHomeworkBackend.entities.dtos;

import myHomeworkBackend.core.entities.Dto;

public class IndividualCustomerForLoginDto implements Dto{

	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
}
