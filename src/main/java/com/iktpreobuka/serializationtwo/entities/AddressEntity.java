package com.iktpreobuka.serializationtwo.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.serializationtwo.security.Views;
import jakarta.persistence.*;


@Entity
public class AddressEntity {

	@Id
	@JsonProperty("ID")
	@JsonView(Views.Private.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonView(Views.Private.class)
	private String street;

	@JsonView(Views.Private.class)
	private String city;

	@JsonView(Views.Private.class)
	private String country;
	private String version;

	@JsonBackReference // ovo polje ne treba da se serijalizuje
	@OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<UserEntity> users = new ArrayList<UserEntity>();

	public AddressEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}
