package com.egencia.mapper;


import com.egencia.modal.TeamView;
import com.egencia.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    TeamView teamToTeamView(Team team);
}
