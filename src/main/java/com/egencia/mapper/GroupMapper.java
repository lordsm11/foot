package com.egencia.mapper;


import com.egencia.entity.Team;
import com.egencia.modal.TeamView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    TeamView teamToTeamView(Team team);
}
