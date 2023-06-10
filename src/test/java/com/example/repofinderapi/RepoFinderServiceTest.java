package com.example.repofinderapi;

import com.example.repofinderapi.service.BranchesService;
import com.example.repofinderapi.service.RepoFinderService;
import com.example.repofinderapi.service.ReposService;
import model.RepoFinderResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RepoFinderServiceTest {

    @Test
    public void testPopulateRepoFinderResponsePositiveCase() {
        // Mocking the ReposService and BranchesService
        ReposService reposServiceMock = Mockito.mock(ReposService.class);
        BranchesService branchesServiceMock = Mockito.mock(BranchesService.class);

        // Creating sample data
        List<HashMap<String, Object>> repositories = new ArrayList<>();
        HashMap<String, Object> testRepository = new HashMap<>();
        testRepository.put("name", "test-repo-1");
        testRepository.put("fork", false);
        HashMap<String, Object> owner = new HashMap<>();
        owner.put("login", "test-user-1");
        testRepository.put("owner", owner);
        repositories.add(testRepository);

        List<HashMap<String, Object>> branches = new ArrayList<>();
        HashMap<String, Object> branch = new HashMap<>();
        branch.put("name", "branch1");
        HashMap<String, Object> commit = new HashMap<>();
        commit.put("sha", "123456789AbcXyz");
        branch.put("commit", commit);
        branches.add(branch);

        when(reposServiceMock.getReposResponse("username")).thenReturn(repositories);
        when(branchesServiceMock.getBranchesResponse("test-user-1", "test-repo-1")).thenReturn(branches);

        RepoFinderService repoFinderService = new RepoFinderService(reposServiceMock, branchesServiceMock);
        RepoFinderResponse response = repoFinderService.populateRepoFinderResponse("username");

        assertEquals(1, response.getRepositories().size());
        assertEquals("test-repo-1", response.getRepositories().get(0).getRepositoryName());
        assertEquals("test-user-1", response.getRepositories().get(0).getOwnerLogin());
        assertEquals(1, response.getRepositories().get(0).getBranches().size());
        assertEquals("branch1", response.getRepositories().get(0).getBranches().get(0).getBranchName());
        assertEquals("123456789AbcXyz", response.getRepositories().get(0).getBranches().get(0).getSha());
    }

    @Test
    public void testPopulateRepoFinderResponseFork() {
        // Mocking the ReposService and BranchesService
        ReposService reposServiceMock = Mockito.mock(ReposService.class);
        BranchesService branchesServiceMock = Mockito.mock(BranchesService.class);

        // Creating sample data
        List<HashMap<String, Object>> repositories = new ArrayList<>();
        HashMap<String, Object> testRepository = new HashMap<>();
        testRepository.put("name", "test-repo-1");
        testRepository.put("fork", true);
        HashMap<String, Object> owner = new HashMap<>();
        owner.put("login", "test-user-1");
        testRepository.put("owner", owner);
        repositories.add(testRepository);

        List<HashMap<String, Object>> branches = new ArrayList<>();
        HashMap<String, Object> branch = new HashMap<>();
        branch.put("name", "branch1");
        HashMap<String, Object> commit = new HashMap<>();
        commit.put("sha", "123456789AbcXyz");
        branch.put("commit", commit);
        branches.add(branch);

        when(reposServiceMock.getReposResponse("username")).thenReturn(repositories);
        when(branchesServiceMock.getBranchesResponse("test-user-1", "test-repo-1")).thenReturn(branches);

        RepoFinderService repoFinderService = new RepoFinderService(reposServiceMock, branchesServiceMock);
        RepoFinderResponse response = repoFinderService.populateRepoFinderResponse("username");

        assertEquals(0, response.getRepositories().size());
    }

}
