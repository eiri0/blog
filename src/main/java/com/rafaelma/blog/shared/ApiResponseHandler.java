package com.rafaelma.blog.shared;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ApiResponseHandler {

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setErrors(null);
        response.setErrorCode(0);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(List<String> errors, String message, int errorCode) {
        ApiResponse<T> response = new ApiResponse<>();
        String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        response.setErrors(errors);
        response.setErrorCode(errorCode);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(String error, String message, int errorCode) {
        String path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
        return error(Arrays.asList(error), message, errorCode);
    }
}
