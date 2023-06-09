package com.ead.course.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.course.assembler.ModuleModelAssembler;
import com.ead.course.entity.ModuleEntity;
import com.ead.course.model.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModuleUseCase {
	private final CourseService courseService;
	private final ModuleService moduleService;
	private final ModuleModelAssembler assembler;

	public ModuleModel saveModule(UUID courseId, ModuleModel moduleModelRequest) {

		var findCourseEntity = courseService.findCourseEntity(courseId);
		ModuleEntity moduleEntity = assembler.toEntity(moduleModelRequest);

		moduleEntity.setCourse(findCourseEntity);

		ModuleEntity saveEntity = moduleService.save(moduleEntity);
		ModuleModel moduleModelResponse = assembler.toModel(saveEntity);

		return moduleModelResponse;
	}

	public void deleteModule(UUID courseId, UUID moduleId) {
		var moduleEntity = moduleService.findModuleByCourse(courseId, moduleId);
		moduleService.delete(moduleEntity);		
	}

	public void updateModule(UUID courseId, UUID moduleId, ModuleModel requestedModuleModifications) {
		// para campos não obrigatorios posso definir que se não passar o campo, ou seja se o campo vier null , vou manter o valor atual
		// só será possível atualizar o valor por outro. caso queira que um titulo por exemplo seja modificado de abc para null por exemplo,
		// que passe o titulo como string "nulo" ai esse sera o novo valor
		var modificationsNotAllowed = moduleService.findModuleByCourse(courseId, moduleId);
		// new BeanWrapper(); 
		// BeanUtils.copyProperties(requestedModuleModifications, modificationsNotAllowed, "title");
		
		var moduleModifications = assembler.toEntity(requestedModuleModifications);
					
	}
	

	// https://stackoverflow.com/questions/17417345/beanutils-copyproperties-api-to-ignore-null-and-specific-propertie
		
/*	
	public static void copyNonNullProperties(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for (PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null)
	            emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
*/

}
