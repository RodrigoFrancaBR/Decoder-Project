package com.ead.authuser.clienthttp;

import com.ead.authuser.model.CourseModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Component
public class CourseClient {

    private final RestTemplate restTemplate;
    private final String REQUEST_URI = "http://localhost:8082";

    public PagedModel<CourseModel> getAllCoursesByUser(UUID userId, Pageable pageable) {
        final var url = buildUrl(userId, pageable);
        log.info("requesting URL: {} ", url);
        var responseType = new ParameterizedTypeReference<PagedModel<CourseModel>>() {};
        try {
            var result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            log.info("requested response: {}", result);
            return result.getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("Error: {}", exception);
            throw new RuntimeException("erro de integração");
        }
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