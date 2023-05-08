package com.ead.authuser.dto.errors;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class ValidationErrorsOutputDto {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutputDto> fieldErros = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErros.add(fieldError);
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErros.size();
    }
}
