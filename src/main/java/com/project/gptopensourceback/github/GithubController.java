package com.project.gptopensourceback.github;

import com.project.gptopensourceback.global.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubServiceImpl githubServiceImpl;

    @GetMapping("/users/{username}/repos")
    public String listUserRepositories(@PathVariable("username") String username) {
        return githubServiceImpl.listRepositories(username);
    }

    @GetMapping("/repos/search")
    public ResponseEntity<CustomApiResponse<?>> searchRepositories(
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "lang", required = false) String lang) {
        ResponseEntity<CustomApiResponse<?>> response = githubServiceImpl.searchRepositories(keyword, page, lang);
        return response;
    }
}
