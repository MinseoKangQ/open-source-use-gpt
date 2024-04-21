package com.project.gptopensourceback.gpt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @GetMapping("/ask/{prompt}")
    public String askGpt(@PathVariable("prompt") String prompt) {
        return gptService.askGpt(prompt);
    }

}
