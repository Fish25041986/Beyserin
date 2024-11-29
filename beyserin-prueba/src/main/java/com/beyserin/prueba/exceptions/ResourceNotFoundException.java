package com.beyserin.prueba.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	private String message;

	public ResourceNotFoundException(String message) {
        this.message = message;
	}
}
