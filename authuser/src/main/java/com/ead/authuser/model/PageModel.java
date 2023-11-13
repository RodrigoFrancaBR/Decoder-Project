package com.ead.authuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageModel {
    private int size;
    private Long totalElements;
    private int totalPages;
    private int number;
}
