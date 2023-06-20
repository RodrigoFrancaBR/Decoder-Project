package com.ead.course.assembler;

import com.ead.course.controllers.ModuleController;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ModuleModelAssembler extends RepresentationModelAssemblerSupport<ModuleEntity, ModuleModel> {

	public ModuleModelAssembler() {
		super(ModuleController.class, ModuleModel.class);
	}

	@Override
	public abstract ModuleModel toModel(ModuleEntity moduleEntity);
}
