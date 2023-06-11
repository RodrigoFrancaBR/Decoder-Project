package com.ead.authuser.assembler;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserEntityAssembler {

	@Mapping(target = "userName", source = "model.nickName")
	@Mapping(defaultValue = "ACTIVE", target = "userStatus")
	@Mapping(defaultValue = "STUDENT", target = "userType")
	UserEntity toEntity(UserModel model);

	public default void copyNonNullProperties(UserModel source, UserEntity target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public default String[] getNullPropertyNames(UserModel source) {
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
