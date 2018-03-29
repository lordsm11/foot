package com.egencia.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.egencia.entity.Team;
import com.egencia.mapper.TeamMapper;
import com.egencia.modal.TeamView;
import com.egencia.repository.MatchRepository;
import com.egencia.repository.TeamRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.assertj.core.api.Assertions;

import static org.mockito.Mockito.when;

public class FootServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private FootService footService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private MatchRepository matchRepository;

    @Test
    public void testGetTeams() {
        Team team1 = Team.builder().id("id1").iso2("iso1").name("name1").build();
        Team team2 = Team.builder().id("id2").iso2("iso2").name("name2").build();
        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));
        List<TeamView> teamViews = footService.getTeams();
        Assertions.assertThat(teamViews.get(0).getName()).isEqualTo("name1");
        Assertions.assertThat(teamViews.get(1).getName()).isEqualTo("name2");
    }

}
