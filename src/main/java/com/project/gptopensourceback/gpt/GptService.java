package com.project.gptopensourceback.gpt;

import com.project.gptopensourceback.global.response.CustomApiResponse;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
        GptRequest request = new GptRequest("gpt-4o", Collections.singletonList(userMessage));
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

    public ResponseEntity<CustomApiResponse<?>> askGptProjectInfo(ProjectInfoReqDto dto) {

        String prompt = "Repository 이름 :" + dto.getName() +
                        "Repository 소유자 : " + dto.getOwner() +
                        "Repository 설명 : " + dto.getDescription() +
                        "Repository 링크 : " + dto.getUrl() +

                "\n" + """
                
                이 오픈소스에 대해 알려주세요. 답변은 다음 형식에 맞춰서 텍스트로 주세요.
                                
                0. 필요한 정보는 프로젝트 한 줄 소개, 프로젝트 활용 방안 이 두 가지입니다.
                1. 프로젝트 한 줄 소개에 대한 답변은 대괄호([])로 감싸주세요.
                2. 프로젝트 활용 방안에 대한 답변은 &로 감싸주세요.
                3. 프로젝트 활용 방안은 많이 알려주면 좋습니다.
                4. 프로젝트 활용 방안의 내용이 여러 문장인 경우, '\n' 으로  구분해주세요.
                                
                다음은 응답 예시입니다.
                [이 프로젝트는 어떠한 프로젝트입니다.]
                &해당 프로젝트는 어느 분야에 유용합니다.&
                """;


        String url = "https://api.openai.com/v1/chat/completions";

        GptRequest.Message userMessage = new GptRequest.Message("user", prompt);
        GptRequest request = new GptRequest("gpt-4o", Collections.singletonList(userMessage));
        HttpEntity<GptRequest> entity = new HttpEntity<>(request);

        try {
            ResponseEntity<GptResponse> response = gptRestTemplate.postForEntity(url, entity, GptResponse.class);
            if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
                GptResponse.Message message = response.getBody().getChoices().get(0).getMessage();
                if (message != null) {
//                    System.out.println(message.getContent());
//
                    String projectIntroduction = message.getContent().substring(message.getContent().indexOf("[") + 1, message.getContent().indexOf("]"));
//                    System.out.println("====");
//                    System.out.println(projectIntroduction);
//                    System.out.println("====");
//
                    String projectUsefulWhere = message.getContent().substring(message.getContent().indexOf("&") + 1, message.getContent().lastIndexOf("&"));
//                    System.out.println("====");
//                    System.out.println(projectUsefulWhere);
//                    System.out.println("====");

                    ProjectInfoResDto data = ProjectInfoResDto.builder()
                            .projectIntroduction(projectIntroduction)
                            .projectUsefulWhere(projectUsefulWhere)
                            .build();

                    CustomApiResponse<ProjectInfoResDto> responseBody = CustomApiResponse.createSuccess(201, data,
                            "응답을 받았습니다.");
                    return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseBody);
                }
            }
            return ResponseEntity.internalServerError().body(CustomApiResponse.createFailWithoutData(500, "GPT 서버로부터 응답을 받지 못했습니다."));

        } catch (HttpClientErrorException e) {
            System.err.println("API 호출 도중 에러 발생: " + e.getStatusCode());
            System.err.println("에러 Body: " + e.getResponseBodyAsString());
            return ResponseEntity.internalServerError().body(CustomApiResponse.createFailWithoutData(500, e.getLocalizedMessage()));
        }

    }
}
