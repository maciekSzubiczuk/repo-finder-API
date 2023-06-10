package com.example.repofinderapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RepoFinderResponse {
    List<RepoFinderResponseItem> repositories;

    public RepoFinderResponse() {
        this.repositories = new ArrayList<>();
    }
}
