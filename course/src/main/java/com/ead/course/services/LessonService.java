package com.ead.course.services;

import java.util.UUID;

public interface LessonService {

	void deleteAllLessonsByModuleId(UUID moduleId);

}
