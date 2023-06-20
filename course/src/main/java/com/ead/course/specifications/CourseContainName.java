package com.ead.course.specifications;

import com.ead.course.entity.CourseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CourseContainName implements Specification<CourseEntity> {

	private static final long serialVersionUID = -3118835657071890563L;
	private String name;

	@Override
	public Predicate toPredicate(Root<CourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		var pattern = new StringBuilder()
				.append("%")
				.append(name)
				.append("%")
				.toString();
		return builder.like(root.get("name"), pattern);
	}
}
