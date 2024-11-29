package com.beyserin.prueba.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	 //Cuando no cumple con ninguno de los dos criterios del tipo de documento
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setTimestamp(LocalDateTime.now());
	        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	        errorResponse.setPath(request.getDescription(false));
	        errorResponse.setError(ex.getMessage());

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    
	 }
	
	 //Cuando se busca el cliente, pero no cumple con las dos condiciones
	 @ExceptionHandler(NoDataFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setTimestamp(LocalDateTime.now());
	        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	        errorResponse.setPath(request.getDescription(false));
	        errorResponse.setError(ex.getMessage());

	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	 
	 //Cuando falla por alguna razón el proceso en el metodo obtenerClienteMockeado
	 @ExceptionHandler(InternalServerException.class)
	    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException ex, WebRequest request) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setTimestamp(LocalDateTime.now());
	        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        errorResponse.setPath(request.getDescription(false));
	        errorResponse.setError(ex.getMessage());

	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	 }
	 
	//Cuando intentan ingresar a una ruta inexistente
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setError(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
