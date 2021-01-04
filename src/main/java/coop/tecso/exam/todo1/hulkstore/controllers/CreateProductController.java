package coop.tecso.exam.todo1.hulkstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateProductRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.CreateProductService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.CreateProductHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.CreateProductResponse;

@RestController

public class CreateProductController {

	private CreateProductService service;

	public CreateProductController(CreateProductService service) {
		this.service = service;
	}
	
	@PostMapping("/api/products")
	public ResponseEntity<CreateProductResponse> handleRequest( @RequestBody CreateProductHttpRequest requestBody ) {
		
		CreateProductRequest request = requestBody.toServiceRequest();
		
		service.execute(request);
		
		CreateProductResponse responseBody = CreateProductResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}

}
