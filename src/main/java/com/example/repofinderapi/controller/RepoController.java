package com.example.repofinderapi.controller;

import com.example.repofinderapi.service.ReposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class RepoController {

    private final ReposService reposService;

    @Autowired
    RepoController(ReposService reposService){
        this.reposService = reposService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUserRepos(@PathVariable String username) {
        List<HashMap<String, Object>> reposResponse = reposService.getReposResponse(username);
        System.out.println(reposResponse);
        return ResponseEntity.ok("Requested username: "+username);
    }

}
