package edu.Vehicles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import edu.Vehicles.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//Exception Handler Methods
	//This Method will be executed automatically 
	//when the specified exception occours
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex, HttpServletRequest req){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.failure(HttpStatus.NOT_FOUND.value(), "Not Found",ex.getMessage(),req ));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex, HttpServletRequest req){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.failure(500, "Not Found",ex.getMessage(),req ));
	}
	
}
