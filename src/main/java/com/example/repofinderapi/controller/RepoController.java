package com.example.repofinderapi.controller;

import com.example.repofinderapi.service.BranchesService;
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
    private final BranchesService branchesService;

    @Autowired
    RepoController(ReposService reposService, BranchesService branchesService){
        this.reposService = reposService;
        this.branchesService = branchesService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUserRepos(@PathVariable String username) {
        List<HashMap<String, Object>> reposResponse = reposService.getReposResponse(username);
        List<HashMap<String, Object>> branchesResponse = branchesService.getBranchesResponse(username,"repo-finder-API");
        System.out.println(reposResponse);
        System.out.println();
        System.out.println(branchesResponse);
        return ResponseEntity.ok("Requested username: "+username);
    }

}
