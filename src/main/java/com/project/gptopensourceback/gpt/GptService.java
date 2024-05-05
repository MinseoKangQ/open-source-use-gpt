package com.project.gptopensourceback.gpt;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class GptService {

    private final RestTemplate gptRestTemplate;

    public String askGpt(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";

        GptRequest.Message userMessage = new GptRequest.Message("user", prompt);
        GptRequest request = new GptRequest("gpt-4", Collections.singletonList(userMessage));
        HttpEntity<GptRequest> entity = new HttpEntity<>(request);

        try {
            ResponseEntity<GptResponse> response = gptRestTemplate.postForEntity(url, entity, GptResponse.class);
            if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
                GptResponse.Message message = response.getBody().getChoices().get(0).getMessage();
                if (message != null) {
                    return message.getContent();
                }
            }
            return "GPT 서버로부터 응답을 받지 못했습니다.";
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP error during API call: " + e.getStatusCode());
            System.err.println("Error body: " + e.getResponseBodyAsString());
            return "Error: " + e.getMessage();
        }
    }
}
