package com.project.gptopensourceback.github;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/users/{username}/repos")
    public String listUserRepositories(@PathVariable("username") String username) {
        return githubService.listRepositories(username);
    }
}
