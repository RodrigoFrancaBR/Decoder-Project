package com.ead.authuser.dto;

import org.springframework.hateoas.Link;

public interface UserLinkBuilder<T> {
	
	T buildLinkWithSelfAndRelation(Link linkWithSelfRel, Link linkWithRel);
}
