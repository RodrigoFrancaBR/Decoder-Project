package com.ead.course.clienthttp;

import com.ead.course.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final String REQUEST_URI = "http://localhost:8087";

    public PagedModel<UserModel> getAllUsersByCourse(UUID courseId, Pageable pageable) {
        final var url = buildUrl(courseId, pageable);
        log.info("requesting URL: {} ", url);
        var responseType = new ParameterizedTypeReference<PagedModel<UserModel>>() {};
        try {
            ResponseEntity<PagedModel<UserModel>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            log.info("requested response: {}", result);
            return result.getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("Error: {}", exception);
            throw new RuntimeException("Erro de integração");
        }
    }

    private String buildUrl(UUID courseId, Pageable pageable) {
        StringBuilder builder = new StringBuilder(REQUEST_URI);
        return builder.append(getEndpoint(courseId))
            .append(getPage(pageable))
            .append(getPageSize(pageable))
            .append(getPageSort(pageable))
            .toString();
    }

    private String getEndpoint(UUID courseId) {
        return MessageFormat.format("/users?courseId={0}", courseId);
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