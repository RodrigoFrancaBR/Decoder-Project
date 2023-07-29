package com.ead.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CourseApplication.class);

    public static void main(String[] args) {
        logger.info("OLA LOGGER");
        SpringApplication.run(CourseApplication.class, args);
    }

}
