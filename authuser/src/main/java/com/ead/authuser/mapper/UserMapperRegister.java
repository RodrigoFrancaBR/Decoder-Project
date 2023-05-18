package com.ead.authuser.mapper;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperRegister {
    @Mapping(target = "userName", source = "userDto.nickName")
    @Mapping(defaultValue = "ACTIVE", target = "userStatus")
    @Mapping(defaultValue = "STUDENT", target = "userType")
    UserModel toModel(UserDto userDto);
}