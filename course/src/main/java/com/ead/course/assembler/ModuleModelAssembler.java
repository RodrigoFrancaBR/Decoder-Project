package com.ead.course.assembler;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.ead.course.controllers.ModuleController;
import com.ead.course.controllers.util.LinksFactory;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ModuleModelAssembler extends RepresentationModelAssemblerSupport<ModuleEntity, ModuleModel> {
	@Autowired
	private LinksFactory factory;
	
	public ModuleModelAssembler() {
		super(ModuleController.class, ModuleModel.class);
	}

	public abstract ModuleEntity toEntity(ModuleModel moduleModel);
/*
	public void copyPropertiesCannotBeModified(ModuleEntity moduleEntity, ModuleEntity entityMapped) {
		entityMapped.setModuleId(moduleEntity.getModuleId());
		entityMapped.setLastUpdateDate(moduleEntity.getLastUpdateDate());
		entityMapped.setCreationDate(moduleEntity.getCreationDate());
		entityMapped.setModules(moduleEntity.getModules());		;
	}
	*/
	@Override	
	public abstract ModuleModel toModel(ModuleEntity moduleEntity);
	/*
	@AfterMapping
	protected void addLinks(@MappingTarget ModuleModel moduleModel, ModuleEntity moduleEntity) {
		var userWithSelfAndRelationLinks = createModelWithId(moduleEntity.getModuleId(), moduleEntity)
				.add(factory.linkToModules());

		moduleModel.add(userWithSelfAndRelationLinks.getLinks());

	}*/

}
