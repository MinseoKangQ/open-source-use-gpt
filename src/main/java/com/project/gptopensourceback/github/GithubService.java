package com.project.gptopensourceback.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final RestTemplate githubRestTemplate;

    public String listRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        return githubRestTemplate.getForObject(url, String.class);
    }
}
