package com.ead.course.specifications;

import com.ead.course.entity.CourseEntity;
import com.ead.course.enums.CourseStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CourseWithStatus implements Specification<CourseEntity> {

	private static final long serialVersionUID = -3118835657071890563L;
	private CourseStatus courseStatus;

	@Override
	public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("courseStatus"), courseStatus);
	}
}
