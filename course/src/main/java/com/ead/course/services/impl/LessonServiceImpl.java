package com.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.ead.course.repository.LessonRepository;
import com.ead.course.services.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	
	private final LessonRepository lessonRepository;
}
