package com.ead.authuser.controllers.links;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPoint {

	@Autowired
	private LinksFactory factory;

	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		return rootEntryPointModel.add(factory.linkToUsers());
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
}
