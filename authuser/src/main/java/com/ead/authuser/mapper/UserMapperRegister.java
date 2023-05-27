package com.ead.authuser.mapper;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.model.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperRegister {
    @Mapping(target = "userName", source = "userDto.nickName")
    @Mapping(defaultValue = "ACTIVE", target = "userStatus")
    @Mapping(defaultValue = "STUDENT", target = "userType")
    UserEntity toModel(UserModel userDto);
}
