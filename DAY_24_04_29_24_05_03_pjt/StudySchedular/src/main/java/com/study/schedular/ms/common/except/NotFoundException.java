package com.study.schedular.ms.common.except;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such member")
public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = -5677184801060457789L;

	public NotFoundException(String message) {
		super(message);
	}
}