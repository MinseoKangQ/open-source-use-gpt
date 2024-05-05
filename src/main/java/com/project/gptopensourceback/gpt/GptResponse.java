package com.project.gptopensourceback.gpt;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 *   "id": "chatcmpl-9LYxxAn9DXxBv0LyUN46ZXdYyPYYA",
 *   "object": "chat.completion",
 *   "created": 1714926325,
 *   "model": "gpt-4-0613",
 *   "choices": [
 *     {
 *       "index": 0,
 *       "message": {
 *         "role": "assistant",
 *         "content": "Hello! How can I assist you today?"
 *       },
 *       "logprobs": null,
 *       "finish_reason": "stop"
 *     }
 *   ],
 *   "usage": {
 *     "prompt_tokens": 8,
 *     "completion_tokens": 9,
 *     "total_tokens": 17
 *   },
 *   "system_fingerprint": null
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GptResponse {
    private List<Choice> choices;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
        private Object logprobs;
        private String finishReason;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}