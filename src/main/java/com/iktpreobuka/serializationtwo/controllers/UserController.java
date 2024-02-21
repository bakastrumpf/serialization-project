package com.iktpreobuka.serializationtwo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.serializationtwo.controllers.dto.UserRegistryDTO;
import com.iktpreobuka.serializationtwo.controllers.util.RESTError;
import com.iktpreobuka.serializationtwo.entities.AddressEntity;
import com.iktpreobuka.serializationtwo.entities.UserEntity;
import com.iktpreobuka.serializationtwo.security.Views;

@RestController
@RequestMapping("/users")

public class UserController {
	
	@RequestMapping(method = RequestMethod.POST, path = "")
	public ResponseEntity<?> createUser(@RequestBody UserRegistryDTO newUser){
		UserEntity user = new UserEntity();
		user.setEmail(newUser.getEmail());
		user.setName(newUser.getName());
		user.setId(3);
		user.setPassword("123");
		// TODO save user to DB
		return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping (method = RequestMethod.GET, path = "/{id}")
	@JsonView(Views.Admin.class)
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		// TODO preuzmi sve korisnike i pretraži po ID
		// TODO ako nađemo korisnika s traženim ID vraćamo objekat kao odgovor
		// TODO ako ga ne nađemo, vraćamo poruku
		try {
			List<UserEntity> users = getDummyDB();
			for(UserEntity u: users) {
				if(u.getId().equals(id)) {
					return new ResponseEntity<UserEntity>(u,HttpStatus.OK);
				}
			}
		// ako tražimo jednog korisnika s ID 3, vratiće nam određenu grešku:
		// za ostale nepostojeće korisnike vraća poruku o grešci	
		//	if (id == 3)
		//		throw new IOException();
			return new ResponseEntity<RESTError>(new RESTError(1, 
					"User not found"), HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			//TODO handle exception
			return new ResponseEntity<RESTError>(new RESTError(2,
					"While requesting user from DB, an error occured. Error message."
					+e.getMessage() + ".\n Stack trace: " + e.getStackTrace()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@RequestMapping(method = RequestMethod.GET, path = "/")
	@JsonView(Views.Public.class)
	public List<UserEntity> getAll() {
		return getDummyDB();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/public")
	@JsonView(Views.Public.class)
	public List<UserEntity> getAllPublic() {
		return getDummyDB();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/private")
	@JsonView(Views.Private.class)
	public List<UserEntity> getAllPrivate() {
		return getDummyDB();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	@JsonView(Views.Admin.class)
	public List<UserEntity> getAllAdmin() {
		return getDummyDB();
	}

	public List<UserEntity> getDummyDB() {
		List<UserEntity> list = new ArrayList<>();
		AddressEntity addr = new AddressEntity();
		addr.setId(1);
		addr.setStreet("Glavna Ulica 1");
		addr.setCity("Novi Sad");
		addr.setCountry("Srbija");

		UserEntity ue = new UserEntity();
		ue.setId(1);
		ue.setDateOfBirth(new Date());
		ue.setEmail("user@example.com");
		ue.setName("Vladimir");
		ue.setPassword("password1234");
		ue.setVersion(0);
		ue.setAddress(addr);

		UserEntity ue1 = new UserEntity();
		ue1.setId(2);
		ue1.setDateOfBirth(new Date());
		ue1.setEmail("user2@example.com");
		ue1.setName("Milan");
		ue1.setPassword("password4321");
		ue1.setVersion(0);
		ue1.setAddress(addr);
		addr.getUsers().add(ue);
		addr.getUsers().add(ue1);
		list.add(ue);
		list.add(ue1);
		return list;
	}
	
}
