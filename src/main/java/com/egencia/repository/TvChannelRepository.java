package com.egencia.repository;

import com.egencia.entity.TvChannel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TvChannelRepository extends MongoRepository<TvChannel, String> {

}