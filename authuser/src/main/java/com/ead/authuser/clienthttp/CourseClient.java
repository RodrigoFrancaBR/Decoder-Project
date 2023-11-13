package com.ead.authuser.clienthttp;

import com.ead.authuser.model.CourseModel;
import com.ead.authuser.model.ResponseModel;
import com.ead.authuser.model.ResponsePageModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Component
public class CourseClient {

    private final RestTemplate restTemplate;
    private final String REQUEST_URI = "http://localhost:8082";

    public ResponseEntity<ResponseModel> getAllCoursesByUser(UUID userId, Pageable pageable) {
        ResponseEntity<ResponseModel> response = null;
        final var url = buildUrl(userId, pageable);
        log.info("requesting URL: {} ", url);
        ParameterizedTypeReference<ResponseModel> responseType = new ParameterizedTypeReference<ResponseModel>() {
        };
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseModel.class);
            log.info("requested response: {}", response);
        } catch (HttpStatusCodeException exception) {
            log.error("Error: {}", exception);
        }
        return response;
    }

    private String buildUrl(UUID userId, Pageable pageable) {
        StringBuilder builder = new StringBuilder(REQUEST_URI);
        return builder.append(getEndpoint(userId))
            .append(getPage(pageable))
            .append(getPageSize(pageable))
            .append(getPageSort(pageable))
            .toString();
    }

    private String getEndpoint(UUID userId) {
        return MessageFormat.format("/courses?userId={0}", userId);
    }

    private String getPage(Pageable pageable) {
        return MessageFormat.format("&page={0}", pageable.getPageNumber());
    }

    private String getPageSize(Pageable pageable) {
        return MessageFormat.format("&size={0}", pageable.getPageSize());
    }

    private String getPageSort(Pageable pageable) {
        final var sort = pageable.getSort();
        final var replacement = sort.toString().replaceAll(": ", ",");
        return MessageFormat.format("&sort={0}", replacement);
    }
}