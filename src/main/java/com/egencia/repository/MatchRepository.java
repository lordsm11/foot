package com.egencia.repository;

import com.egencia.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match, String> {

    Match findByName(String name);

    List<Match> findByGroup(String group);

    List<Match> findByHomeTeamOrAwayTeam(String homeTeam, String awayTeam);

}