package com.ead.coursespecifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ead.course.entity.CourseEntity;

public interface CourseWithLevelAndStatusAndNameSpec extends Specification<CourseEntity> {

	@Override
	default Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// TODO Auto-generated method stub
		return null;
	}
}
