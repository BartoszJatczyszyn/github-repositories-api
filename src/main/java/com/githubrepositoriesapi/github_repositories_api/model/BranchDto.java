package com.githubrepositoriesapi.github_repositories_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private String name;
    private String lastCommitSha;
}
