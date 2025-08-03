package com.githubrepositoriesapi.github_repositories_api.service;

import com.githubrepositoriesapi.github_repositories_api.client.GithubClient;
import com.githubrepositoriesapi.github_repositories_api.model.GithubRepository;
import com.githubrepositoriesapi.github_repositories_api.model.RepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubService {

    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryDto> getUserRepositories(String username) {
        List<GithubRepository> repositories = githubClient.fetchRepositories(username);
        return repositories.stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> new RepositoryDto(
                        repo.getName(),
                        repo.getOwner().getLogin(),
                        githubClient.fetchBranches(username, repo.getName())
                ))
                .toList();
    }
}
