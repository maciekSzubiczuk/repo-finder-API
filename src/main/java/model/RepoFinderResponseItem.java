package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RepoFinderResponseItem {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;

    public RepoFinderResponseItem(){
        this.branches = new ArrayList<>();
    }
}
