package com.ead.course.services.impl;

import com.ead.course.repository.CourseUserRepository;
import com.ead.course.services.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseUserServiceImpl implements CourseUserService {

    private final CourseUserRepository repository;
}