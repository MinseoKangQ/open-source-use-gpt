package com.project.gptopensourceback.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Value("${github.api.key}")
    private String githubApiKey;

    @Value("${gpt.api.key}")
    private String gptApiKey;

    @Bean(name = "githubRestTemplate")
    public RestTemplate githubRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "token " + githubApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

    @Bean(name = "gptRestTemplate")
    public RestTemplate gptRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + gptApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}