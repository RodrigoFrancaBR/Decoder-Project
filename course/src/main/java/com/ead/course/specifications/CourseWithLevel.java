package com.ead.course.specifications;

import com.ead.course.entity.CourseEntity;
import com.ead.course.enums.CourseLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CourseWithLevel implements Specification<CourseEntity> {

	private static final long serialVersionUID = -3118835657071890563L;
	private CourseLevel courseLevel;

	@Override
	public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("courseLevel"), courseLevel);
	}
}
