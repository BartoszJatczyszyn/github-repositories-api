package com.githubrepositoriesapi.github_repositories_api.controller;

import com.githubrepositoriesapi.github_repositories_api.exception.UserNotFoundException;
import com.githubrepositoriesapi.github_repositories_api.model.ErrorResponse;
import com.githubrepositoriesapi.github_repositories_api.model.RepositoryDto;
import com.githubrepositoriesapi.github_repositories_api.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getUserRepositories(username));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }
}

