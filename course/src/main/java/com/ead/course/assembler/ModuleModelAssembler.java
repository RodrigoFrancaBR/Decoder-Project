package com.ead.course.assembler;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
	 * public void copyPropertiesCannotBeModified(ModuleEntity
	 * modificationsNotAllowed, ModuleEntity moduleModifications) {
	 * entityMapped.setModuleId(moduleEntity.getModuleId());
	 * entityMapped.setLastUpdateDate(moduleEntity.getLastUpdateDate());
	 * entityMapped.setCreationDate(moduleEntity.getCreationDate());
	 * entityMapped.setModules(moduleEntity.getModules()); ; }
	 */

	@Override
	public abstract ModuleModel toModel(ModuleEntity moduleEntity);
	/*
	 * @AfterMapping protected void addLinks(@MappingTarget ModuleModel moduleModel,
	 * ModuleEntity moduleEntity) { var userWithSelfAndRelationLinks =
	 * createModelWithId(moduleEntity.getModuleId(), moduleEntity)
	 * .add(factory.linkToModules());
	 * 
	 * moduleModel.add(userWithSelfAndRelationLinks.getLinks());
	 * 
	 * }
	 */
	
	public static void copyNonNullProperties(ModuleModel source, ModuleEntity target) {
	    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper bean = new BeanWrapperImpl(source);
	    PropertyDescriptor[] propertyDescriptors = bean.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
	        Object propertyValue = bean.getPropertyValue(propertyDescriptor.getName());
	        if (propertyValue == null)
	            emptyNames.add(propertyDescriptor.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}

}
