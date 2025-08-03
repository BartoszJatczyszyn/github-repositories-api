package com.githubrepositoriesapi.github_repositories_api.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String message) {
        super(message);
    }
}
