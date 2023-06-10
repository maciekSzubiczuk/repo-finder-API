package com.example.repofinderapi.controller;

import com.example.repofinderapi.service.BranchesService;
import com.example.repofinderapi.service.RepoFinderService;
import com.example.repofinderapi.service.ReposService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RepoFinderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/repo-finder")
public class RepoController {

    private final RepoFinderService repoFinderService;

    @Autowired
    RepoController(RepoFinderService repoFinderService){
        this.repoFinderService = repoFinderService;
    }

    @GetMapping(value="/{username}",produces=MediaType.APPLICATION_JSON_VALUE)
    public String getUserRepos(@PathVariable String username) throws JsonProcessingException {
        RepoFinderResponse response = repoFinderService.populateRepoFinderResponse(username);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

}
