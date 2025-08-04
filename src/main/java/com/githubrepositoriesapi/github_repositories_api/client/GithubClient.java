package com.githubrepositoriesapi.github_repositories_api.client;

import com.githubrepositoriesapi.github_repositories_api.exception.UserNotFoundException;
import com.githubrepositoriesapi.github_repositories_api.model.BranchDto;
import com.githubrepositoriesapi.github_repositories_api.model.GithubBranch;
import com.githubrepositoriesapi.github_repositories_api.model.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GithubClient {

    private static final String BASE_URL = "https://api.github.com";

    private final RestTemplate restTemplate;

    public List<GithubRepository> fetchRepositories(String username) {
        String url = BASE_URL + "/users/{username}/repos";
        try {
            GithubRepository[] repositories = restTemplate.getForObject(url, GithubRepository[].class, username);
            if (repositories == null) {
                throw new UserNotFoundException("User not found");
            }
            return Arrays.stream(repositories)
                    .filter(repo -> !repo.isFork())
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found");
            }
            throw e;
        }
    }

    public List<BranchDto> fetchBranches(String username, String repoName) {
        String url = BASE_URL + "/repos/{username}/{repo}/branches";
        GithubBranch[] branches = restTemplate.getForObject(url, GithubBranch[].class, username, repoName);
        if (branches == null) {
            return List.of();
        }
        return Arrays.stream(branches)
                .map(branch -> new BranchDto(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
    }
}
