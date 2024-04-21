package com.project.gptopensourceback.global.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomApiResponse<T>{

    private int status;
    private T data;
    private String message;

    public static <T> CustomApiResponse<T> createSuccess(int status, T data, String message) {
        return new CustomApiResponse<>(status, data, message);
    }

    public static <T> CustomApiResponse<T> createFailWithoutData(int status, String message) {
        return new CustomApiResponse<>(status, null, message);
    }

    public CustomApiResponse(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

}
