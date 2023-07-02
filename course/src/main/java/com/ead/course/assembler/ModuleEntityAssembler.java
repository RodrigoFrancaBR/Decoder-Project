package com.ead.course.assembler;

import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleEntityAssembler extends EntityAssembler<ModuleModel, ModuleEntity> {
    ModuleEntity toEntity(ModuleModel model);
}
