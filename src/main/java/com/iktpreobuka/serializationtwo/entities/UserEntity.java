package com.iktpreobuka.serializationtwo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.serializationtwo.security.Views;
import jakarta.persistence.*;


@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("ID")
	@JsonView(Views.Public.class)
	private Integer id;

	@JsonView(Views.Public.class)
	private String name;

	@JsonView(Views.Admin.class)
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy hh:mm:ss")
	private Date dateOfBirth;

	@JsonView(Views.Admin.class)
	private String email;

	@JsonIgnore // jer ne želimo da se lozinka IKAD serijalizuje
	private String password;

	private Integer version;

	@JoinColumn(name = "address_id")
	@JsonView(Views.Private.class)
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private AddressEntity address;

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(Integer id, String name, Date dateOfBirth, 
			String email, String password, Integer version,
			AddressEntity address) {
		super();
		this.id = id;
		this.name = name;
		
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.version = version;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

}
