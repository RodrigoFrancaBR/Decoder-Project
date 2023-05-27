package com.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.ead.course.repository.CourseRepository;
import com.ead.course.services.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

}
