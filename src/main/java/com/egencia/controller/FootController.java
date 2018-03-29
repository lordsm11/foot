package com.egencia.controller;

import com.egencia.modal.MatchView;
import com.egencia.service.FootService;
import com.egencia.service.InitDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"}, maxAge = 3600)
public class FootController {

    @Autowired
    private FootService footService;

    @Autowired
    private InitDatabaseService initDatabaseService;

    @GetMapping(value = "/init")
    @ResponseBody
    public ResponseEntity init() {
        initDatabaseService.initData();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/teams")
    @ResponseBody
    public ResponseEntity teams() {
        return ResponseEntity.ok().body(footService.getTeams());
    }

    @GetMapping(value = "/groups")
    @ResponseBody
    public ResponseEntity groups() {
        return ResponseEntity.ok().body(footService.getGroups());
    }

    @GetMapping(value = "/match")
    @ResponseBody
    public ResponseEntity matchs() {
        return ResponseEntity.ok().body(footService.getMatchs());
    }

    @GetMapping(value = "/match/group/{group}")
    @ResponseBody
    public ResponseEntity matchByGroup(@PathVariable String group) {
        return ResponseEntity.ok().body(footService.getMatchsByGroup(group));
    }

    @GetMapping(value = "/match/team/{team}")
    @ResponseBody
    public ResponseEntity matchByTeam(@PathVariable String team) {
        return ResponseEntity.ok().body(footService.getMatchsByTeam(team));
    }

    @PostMapping(value = "/match/score")
    @ResponseBody
    public ResponseEntity setScore(@RequestBody List<MatchView> matchViews) {
        footService.setScores(matchViews);
        return ResponseEntity.noContent().build();
    }

}
