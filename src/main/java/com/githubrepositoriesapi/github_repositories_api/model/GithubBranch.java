package com.githubrepositoriesapi.github_repositories_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubBranch {
    private String name;
    private Commit commit;

    @Data
    @NoArgsConstructor
    public static class Commit {
        private String sha;
    }
}
