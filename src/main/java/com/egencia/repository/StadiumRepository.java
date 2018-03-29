package com.egencia.repository;

import com.egencia.entity.Stadium;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StadiumRepository extends MongoRepository<Stadium, String> {

}