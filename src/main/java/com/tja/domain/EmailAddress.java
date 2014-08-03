package com.tja.domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@Embeddable
public class EmailAddress {

	public EmailAddress() {
		
	}
	
	public EmailAddress(String email) {
		this.email = email;
	}
	
	@Email
	@NotBlank
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
