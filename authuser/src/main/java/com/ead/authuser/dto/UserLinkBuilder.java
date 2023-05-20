package com.ead.authuser.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public interface UserLinkBuilder<DTO> {	
	
	DTO setLinks(List<Link> linkList);
}
