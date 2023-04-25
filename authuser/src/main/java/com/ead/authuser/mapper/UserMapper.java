package com.ead.authuser.mapper;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userName", source = "userDto.nickName")
    UserModel toModel(UserDto userDto);

    List<UserDto> toDto(List<UserModel> allUsersModel);

    @Mapping(target = "nickName", source = "userModel.userName")
    UserDto toDto(UserModel userModel);
}
