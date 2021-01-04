package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.BusinessException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ControllerAdvice

public class ExceptionController {
	
	@ExceptionHandler(value = InvalidFieldException.class)
	public ResponseEntity<Map<String, String>> invalidFieldException(InvalidFieldException exp, WebRequest request) {
		
		Map<String, String> response = new HashMap<>();
		response.put("code", "INVALID_FIELD");
		response.put("message", exp.getMessage());
		
		return ResponseEntity.unprocessableEntity().body(response);
		
	}
	
	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<Map<String, String>> businessException(BusinessException exp, WebRequest request) {
		
		Map<String, String> response = new HashMap<>();
		response.put("code", exp.getCode());
		response.put("message", exp.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		
	}

}
