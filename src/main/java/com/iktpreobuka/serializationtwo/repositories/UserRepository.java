package com.iktpreobuka.serializationtwo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.serializationtwo.entities.UserEntity;
	
	public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	}
