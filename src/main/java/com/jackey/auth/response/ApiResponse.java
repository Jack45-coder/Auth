package com.jackey.auth.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private String successMessage;
    private String errorMessage;
    private T data;

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String message, T data){
        this.success = success;
        if (this.success){
            this.successMessage = message;
        }else {
            this.errorMessage = message;
        }
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String msg, T data){
        return new ApiResponse<>(true, msg, data);
    }

    public static <T> ApiResponse<T> error(String msg){
        return new ApiResponse<>(false, msg, null);
    }
}
