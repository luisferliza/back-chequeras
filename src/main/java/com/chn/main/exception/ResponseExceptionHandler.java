package com.chn.main.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{

	// Todas las demas excepciones
		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request){
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		
		// Excepcion personalizada	
		@ExceptionHandler(ModeloNotFoundException.class )
		public final ResponseEntity<Object> manejarModeloExcepciones(ModeloNotFoundException ex, WebRequest request){
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}

		// Manejo de errores de Input
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			String errores = "";
			for(ObjectError e: ex.getBindingResult().getAllErrors()) {
				errores += e.getObjectName();
			}
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validación fallida", errores);
			
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);		
		}
		
}
