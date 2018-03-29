package com.egencia.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.egencia.entity.Match;
import com.egencia.entity.Stadium;
import com.egencia.entity.Team;
import com.egencia.entity.TvChannel;
import com.egencia.repository.MatchRepository;
import com.egencia.repository.StadiumRepository;
import com.egencia.repository.TeamRepository;
import com.egencia.repository.TvChannelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class InitDatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDatabaseService.class);

    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;
    private final TvChannelRepository tvChannelRepository;
    private final MatchRepository matchRepository;

    public void initData() {
        WorldCupData worldCupData = retrieveDataFromConfig();

        stadiumRepository.deleteAll();
        stadiumRepository.save(worldCupData.getStadiums());

        teamRepository.deleteAll();
        teamRepository.save(worldCupData.getTeams());

        tvChannelRepository.deleteAll();
        tvChannelRepository.save(worldCupData.getTvchannels());

        matchRepository.deleteAll();
        matchRepository.save(worldCupData.getMatchs());

    }

    private WorldCupData retrieveDataFromConfig() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data/data.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, WorldCupData.class);
        } catch (Exception e) {
            LOGGER.error("error on json serialization", e);
            return new WorldCupData();
        }
    }

}

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
class WorldCupData {
    List<Match> matchs;
    List<Stadium> stadiums;
    List<Team> teams;
    List<TvChannel> tvchannels;
}

