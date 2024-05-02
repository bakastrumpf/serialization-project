package com.iktpreobuka.serializationtwo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistryDTO {
	
	@JsonProperty("name")
	public String name;
	@JsonProperty("email")
	public String email;
	
	public UserRegistryDTO() {
		super();
	}
	
	public UserRegistryDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
