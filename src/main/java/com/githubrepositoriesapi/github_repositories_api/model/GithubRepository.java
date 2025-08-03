package com.githubrepositoriesapi.github_repositories_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubRepository {
    private String name;
    private boolean fork;
    private GithubUser owner;
}

