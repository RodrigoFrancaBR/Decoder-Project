package com.ead.course.assembler;

import org.mapstruct.Mapper;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;

@Mapper(componentModel = "spring")
public interface ModuleEntityAssembler extends EntityAssembler<ModuleModel, ModuleEntity> {

	ModuleEntity toEntity(ModuleModel model);
}
