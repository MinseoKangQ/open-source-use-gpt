package com.project.gptopensourceback.gpt;

import com.project.gptopensourceback.global.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ask")
public class GptController {

    private final GptService gptService;

    @GetMapping("/prompt/{prompt}")
    public String askGptPrompt(@PathVariable("prompt") String prompt) {
        return gptService.askGpt(prompt);
    }

    @GetMapping("projectInfo")
    public ResponseEntity<CustomApiResponse<?>> askGptProjectInfo(@RequestBody ProjectInfoReqDto projectInfo) {
        return gptService.askGptProjectInfo(projectInfo);
    }

}
