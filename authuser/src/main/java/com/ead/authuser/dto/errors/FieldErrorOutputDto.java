package com.ead.authuser.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FieldErrorOutputDto {

    private String field;
    private String message;
}
