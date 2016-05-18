package com.yasic.diycode.Bean;

/**
 * Created by Yasic on 2016/5/18.
 */
public class CallbackBean<T extends Object> {
    private String code;
    private String errorMessage;
    private T response;
    public CallbackBean(String code, String errorMessage, T response){
        this.code = code;
        this.errorMessage = errorMessage;
        this.response = response;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResponse() {
        return response;
    }
}
