package com.egencia.mapper;

import com.egencia.entity.Match;
import com.egencia.entity.Team;
import com.egencia.modal.MatchView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(target = "name", source = "match.name" )
    @Mapping(target = "result", source = "match.result" )
    @Mapping(target = "startDate", source = "match.startDate" )
    @Mapping(target = "firstTeam", expression = "java(retrieveCountryName(match.getHomeTeam(),teams))" )
    @Mapping(target = "secondTeam", expression = "java(retrieveCountryName(match.getAwayTeam(),teams))" )
    MatchView matchToMatchView(Match match, List<Team> teams);

    default String retrieveCountryName(String countryId, List<Team> teams) {
        return teams.stream().filter(t -> Objects.equals(t.getId(), countryId)).map(Team::getName).findFirst().orElse("");
    }
}
