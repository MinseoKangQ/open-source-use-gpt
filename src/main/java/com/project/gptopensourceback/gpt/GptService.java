package com.project.gptopensourceback.gpt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GptService {

    private final RestTemplate gptRestTemplate;

    public String askGpt(String prompt) {
        String url = "https://api.openai.com/v1/completions";

        // 'messages' 배열 생성 및 추가
        GptRequest.Message userMessage = new GptRequest.Message("user", prompt);
        GptRequest request = new GptRequest("gpt-3.5-turbo-instruct", Collections.singletonList(userMessage));

        // HttpEntity 객체를 사용하여 요청 본문 구성
        HttpEntity<GptRequest> entity = new HttpEntity<>(request);

        // API 요청 및 응답 처리
        ResponseEntity<GptResponse> response = gptRestTemplate.postForEntity(url, entity, GptResponse.class);

        // 응답 본문에서 메시지 추출
        if (response.getBody() != null && response.getBody().getChoices() != null && !response.getBody().getChoices().isEmpty()) {
            return response.getBody().getChoices().get(0).getMessage();
        } else {
            return "GPT 서버로부터 응답을 받지 못했습니다.";
        }
    }
}
