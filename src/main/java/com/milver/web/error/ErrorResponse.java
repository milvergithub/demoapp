package com.milver.web.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private String code;
    private String message;
    private List<String> errors;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(String code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }

}