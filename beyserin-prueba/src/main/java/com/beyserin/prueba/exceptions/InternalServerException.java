package com.beyserin.prueba.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class InternalServerException extends RuntimeException{

	private String message;

	public InternalServerException(String message) {
        this.message = message;
    }

}
