package com.project.gptopensourceback.github;

import com.project.gptopensourceback.global.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface GithubService {
    String listRepositories(String username);
    ResponseEntity<CustomApiResponse<?>> searchRepositories(String keyword, int page, String lang);
}
