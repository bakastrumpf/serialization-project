package com.iktpreobuka.serializationtwo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.serializationtwo.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

}
