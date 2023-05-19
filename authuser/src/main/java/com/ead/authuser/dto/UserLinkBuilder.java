package com.ead.authuser.dto;

import org.springframework.hateoas.Link;

public interface UserLinkBuilder<T> {
	
	T setLinkWithSelfAndRelation(Link linkWithSelfRel, Link linkWithRel);
}
