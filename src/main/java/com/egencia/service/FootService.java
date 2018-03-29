package com.egencia.service;

import com.egencia.entity.Match;
import com.egencia.entity.Team;
import com.egencia.enumeration.WorldCupGroup;
import com.egencia.mapper.MatchMapper;
import com.egencia.mapper.TeamMapper;
import com.egencia.modal.GroupView;
import com.egencia.modal.MatchView;
import com.egencia.modal.TeamView;
import com.egencia.repository.MatchRepository;
import com.egencia.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FootService {

    private final TeamRepository teamRepository;

    private final MatchRepository matchRepository;

    public List<GroupView> getGroups() {
        return Arrays.stream(WorldCupGroup.values())
                .map(w -> new GroupView(w.name(), w.getLabel()))
                .collect(Collectors.toList());
    }

    public List<TeamView> getTeams() {
        return teamRepository.findAll().stream()
                .map(TeamMapper.INSTANCE::teamToTeamView)
                .collect(Collectors.toList());
    }

    public List<MatchView> getMatchs() {
        List<Match> matchs = matchRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        return matchs.stream()
                .map(m -> MatchMapper.INSTANCE.matchToMatchView(m, teams))
                .collect(Collectors.toList());
    }

    public List<MatchView> getMatchsByGroup(String group) {
        List<Match> matchs = matchRepository.findByGroup(group);
        List<Team> teams = teamRepository.findAll();
        return matchs.stream()
                .map(m -> MatchMapper.INSTANCE.matchToMatchView(m, teams))
                .collect(Collectors.toList());
    }

    public List<MatchView> getMatchsByTeam(String teamName) {
        List<Team> teams = teamRepository.findAll();

        Optional<String> team = teams.stream()
                .filter(t -> Objects.equals(t.getName(), teamName))
                .map(Team::getId).findFirst();

        if(!team.isPresent()) {
            return Collections.emptyList();
        }
        return matchRepository.findByHomeTeamOrAwayTeam(team.get(),team.get()).stream()
                .map(m -> MatchMapper.INSTANCE.matchToMatchView(m, teams))
                .collect(Collectors.toList());
    }

    public void setScores(List<MatchView> matchViews) {
        for (MatchView matchView : matchViews) {
            Match match = matchRepository.findByName(matchView.getName());
            match.setResult(matchView.getResult());
            matchRepository.save(match);
        }
    }
}
