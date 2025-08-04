package com.githubrepositoriesapi.github_repositories_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository {
    private String name;
    private boolean fork;
    private GithubUser owner;
}
