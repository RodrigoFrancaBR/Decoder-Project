package com.ead.course;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {
    private static final Logger logger = LogManager.getLogger(CourseApplication.class);

    public static void main(String[] args) {
        logger.info("OLA LOGGER");
        SpringApplication.run(CourseApplication.class, args);
    }

}
