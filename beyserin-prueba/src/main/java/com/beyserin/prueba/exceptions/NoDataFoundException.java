package com.beyserin.prueba.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class NoDataFoundException extends RuntimeException{

	private String message;

	public NoDataFoundException(String message) {
        this.message = message;
    }	
}
