package com.project.gptopensourceback.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private final RestTemplate githubRestTemplate;

    public String listRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        return githubRestTemplate.getForObject(url, String.class);
    }
}
