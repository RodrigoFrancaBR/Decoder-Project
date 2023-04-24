package com.ead.authuser.mapper;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel toModel(UserDto userDto);
}
