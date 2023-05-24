package com.ead.authuser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.ead.authuser.dto.UserModel;
import com.ead.authuser.entity.UserEntity;


@Mapper(componentModel = "spring", imports = Page.class)
public interface UserMapper {

	@Mapping(target = "userName", source = "userDto.nickName")
	UserEntity toModel(UserModel userDto);

	List<UserModel> toListDto(List<UserEntity> userModelList);

	@Mapping(target = "nickName", source = "userModel.userName")
	UserModel toDto(UserEntity userModel);
}
