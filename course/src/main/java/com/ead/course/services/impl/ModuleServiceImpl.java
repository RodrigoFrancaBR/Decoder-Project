package com.ead.course.services.impl;

import org.springframework.stereotype.Service;

import com.ead.course.repository.ModuleRepository;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
}
