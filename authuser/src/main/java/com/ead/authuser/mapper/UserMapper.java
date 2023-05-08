package com.ead.authuser.mapper;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", imports = Page.class)
public interface UserMapper {

    @Mapping(target = "userName", source = "userDto.nickName")
    UserModel toModel(UserDto userDto);

    List<UserDto> toListDto(List<UserModel> userModelList);

    @Mapping(target = "nickName", source = "userModel.userName")
    UserDto toDto(UserModel userModel);

}
