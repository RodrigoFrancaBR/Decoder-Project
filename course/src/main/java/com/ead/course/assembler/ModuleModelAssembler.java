package com.ead.course.assembler;

import com.ead.course.controllers.ModuleController;
import com.ead.course.controllers.links.LinksModule;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ModuleModelAssembler extends RepresentationModelAssemblerSupport<ModuleEntity, ModuleModel> {

    @Autowired
    private LinksModule links;

    public ModuleModelAssembler() {
        super(ModuleController.class, ModuleModel.class);
    }

    @Override
    public abstract ModuleModel toModel(ModuleEntity moduleEntity);

    @AfterMapping
    protected void addLinks(@MappingTarget ModuleModel moduleModel, ModuleEntity moduleEntity) {
        var moduleByCourseAndModuleId = links.linkToFindByCourseAndModuleId(moduleEntity.getCourse().getCourseId(), moduleEntity.getModuleId());
        moduleModel.add(Link.of(moduleByCourseAndModuleId, "Module ByCourseAndModuleId"));

        // posso fazer para ter 2 links um para obter modulo ByCourseAndModuleId e todos os modulos.
        // moduleModel.add(Link.of(moduleByCourseAndModuleId, "Module ByCourseAndModuleId"))
        // .add(Link.of(allModules, "Modules AllModules"));
    }
}
