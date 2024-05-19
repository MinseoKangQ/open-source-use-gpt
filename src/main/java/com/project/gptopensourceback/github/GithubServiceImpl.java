package com.project.gptopensourceback.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gptopensourceback.global.response.CustomApiResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<CustomApiResponse<?>> searchRepositories(String keyword, int page, String lang) {
        if (keyword == null || keyword.isEmpty()) {
            CustomApiResponse<Object> fail = CustomApiResponse.createFailWithoutData(400, "키워드는 필수입니다.");
            return ResponseEntity.badRequest().body(fail);
        }

        int perPage = 10;
        String url = String.format(
                "https://api.github.com/search/repositories?q=%s+language:%s+is:public&sort=stars&order=desc&page=%d&per_page=%d",
                keyword, lang, page, perPage);

        String response = githubRestTemplate.getForObject(url, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            int totalCount = root.path("total_count").asInt();
            List<RepositoryItem> items = new ArrayList<>();

            for (JsonNode itemNode : root.path("items")) {
                String name = itemNode.path("name").asText();
                String owner = itemNode.path("owner").path("login").asText();
                String description = itemNode.path("description").asText();
                String htmlUrl = itemNode.path("html_url").asText();
                int stars = itemNode.path("stargazers_count").asInt();

                RepositoryItem item = new RepositoryItem(name, owner, description, htmlUrl, stars);
                items.add(item);
            }

            SearchResultData data = new SearchResultData(totalCount, items);
            CustomApiResponse<SearchResultData> success = CustomApiResponse.createSuccess(200, data, "정상적으로 조회되었습니다.");
            return ResponseEntity.ok(success);

        } catch (Exception e) {
            CustomApiResponse<Object> fail = CustomApiResponse.createFailWithoutData(500, "데이터 처리 중 오류가 발생했습니다.");
            return ResponseEntity.internalServerError().body(fail);
        }
    }
}
