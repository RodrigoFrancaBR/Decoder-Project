package com.ead.authuser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    @JsonProperty("_embedded")
    private LinkedHashMap embedded;

    @JsonProperty("_links")
    private LinksModel links;
    @JsonProperty("page")
    private PageModel page;

}
