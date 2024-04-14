package com.project.gptopensourceback.github;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final RestTemplate restTemplate;

    public String listRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        return restTemplate.getForObject(url, String.class);
    }
}
