package com.ead.course.assembler;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.ead.course.entity.CourseEntity;
import com.ead.course.model.CourseModel;

@Mapper(componentModel = "spring")
public interface CourseEntityAssembler {
	
	CourseEntity toEntity(CourseModel model);
	
	public default void copyNonNullProperties(CourseModel source, CourseEntity target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public default String[] getNullPropertyNames(CourseModel source) {
		final BeanWrapper bean = new BeanWrapperImpl(source);
		PropertyDescriptor[] propertyDescriptors = bean.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			Object propertyValue = bean.getPropertyValue(propertyDescriptor.getName());
			if (propertyValue == null)
				emptyNames.add(propertyDescriptor.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

}
