package com.ead.authuser.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
@Getter
public class ValidationErrors {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldError> fieldErros = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldError fieldError = new FieldError(field, message);
        fieldErros.add(fieldError);
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErros.size();
    }
}
