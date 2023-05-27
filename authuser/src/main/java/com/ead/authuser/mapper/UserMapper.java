package com.ead.authuser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.model.UserModel;


@Mapper(componentModel = "spring", imports = Page.class)
public interface UserMapper {

	@Mapping(target = "userName", source = "userDto.nickName")
	UserEntity toModel(UserModel userDto);

	List<UserModel> toListDto(List<UserEntity> userModelList);

	@Mapping(target = "nickName", source = "userModel.userName")
	UserModel toDto(UserEntity userModel);
}
