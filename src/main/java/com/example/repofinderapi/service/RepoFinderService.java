package com.example.repofinderapi.service;

import com.example.repofinderapi.model.Branch;
import com.example.repofinderapi.model.RepoFinderResponse;
import com.example.repofinderapi.model.RepoFinderResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RepoFinderService {

    private final ReposService reposService;
    private final BranchesService branchesService;

    @Autowired
    public RepoFinderService(ReposService reposService, BranchesService branchesService) {
        this.reposService = reposService;
        this.branchesService = branchesService;
    }

    public RepoFinderResponse populateRepoFinderResponse(String username) {
        // Crating a response object
        RepoFinderResponse repoFinderResponse = new RepoFinderResponse();
        List<HashMap<String, Object>> repositories = reposService.getReposResponse(username);
        for (HashMap<String, Object> repository : repositories) {
            // Checking if repo is a fork, if it is then it is not added to response
            if (!(boolean) repository.get("fork")) {
                RepoFinderResponseItem repoFinderResponseItem = new RepoFinderResponseItem();
                String repositoryName = repository.get("name").toString();
                String ownerLogin = ((HashMap<String, Object>) repository.get("owner")).get("login").toString();
                repoFinderResponseItem.setRepositoryName(repositoryName);
                repoFinderResponseItem.setOwnerLogin(ownerLogin);
                List<HashMap<String, Object>> branches =
                        branchesService.getBranchesResponse(ownerLogin, repositoryName);
                for (HashMap<String, Object> branch : branches) {
                    String branchName = branch.get("name").toString();
                    String sha = ((HashMap<String, Object>) branch.get("commit")).get("sha").toString();
                    repoFinderResponseItem.getBranches().add(new Branch(branchName, sha));
                }
                repoFinderResponse.getRepositories().add(repoFinderResponseItem);
            }
        }
        return repoFinderResponse;
    }

}
