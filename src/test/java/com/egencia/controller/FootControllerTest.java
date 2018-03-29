package com.egencia.controller;

import java.util.ArrayList;

import com.egencia.service.FootService;
import com.egencia.service.InitDatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitDatabaseService initDatabaseService;

    @MockBean
    private FootService footService;

    @Test
    public void testInit() throws Exception {
        doNothing().when(initDatabaseService).initData();
        mockMvc.perform(get("/init")).andExpect(status().isNoContent());
        verify(initDatabaseService).initData();
    }

    @Test
    public void testGetTeams() throws Exception {
        when(footService.getTeams()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/teams")).andExpect(status().isOk());
        verify(footService).getTeams();
    }

    @Test
    public void testGetMatchByGroup() throws Exception {
        when(footService.getMatchsByGroup(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/match/group/GROUP_A")).andExpect(status().isOk());
        verify(footService).getMatchsByGroup(eq("GROUP_A"));
    }

    @Test
    public void testGetMatchByTeam() throws Exception {
        when(footService.getMatchsByTeam(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/match/team/tunisia")).andExpect(status().isOk());
        verify(footService).getMatchsByTeam(eq("tunisia"));
    }

}
