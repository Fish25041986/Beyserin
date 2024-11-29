package com.beyserin.prueba.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	
    private LocalDateTime timestamp;
    private int status;
    private String path;
    private String error;
    public ErrorResponse() {
    	
    }

}