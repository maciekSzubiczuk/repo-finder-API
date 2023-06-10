package com.example.repofinderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Branch {
    private String branchName;
    private String sha;
}
