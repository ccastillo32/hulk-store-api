package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.SaveProductRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.SaveProductService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.SaveProductHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.CreateProductResponse;

@RestController

public class SaveProductController {
	
	private SaveProductService service;

	public SaveProductController(SaveProductService service) {
		this.service = service;
	}
	
	@PostMapping("/api/products")
	public ResponseEntity<CreateProductResponse> handleRequest(
			@RequestBody SaveProductHttpRequest requestBody 
	) {
		
		SaveProductRequest request = toServiceRequest(requestBody, null);
		
		service.execute(request, true);
		
		CreateProductResponse responseBody = CreateProductResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/api/products/{productId}")
	public ResponseEntity<Void> handleRequest(
			@PathVariable String productId,
			@RequestBody SaveProductHttpRequest requestBody
	) {
		
		SaveProductRequest request = toServiceRequest(requestBody, productId);
		
		service.execute(request, false);
		
		return ResponseEntity.noContent().build();
		
	}
	
	public SaveProductRequest toServiceRequest(SaveProductHttpRequest requestBody, String productId) {
		SaveProductRequest request = new SaveProductRequest();
		request.setId(productId != null ? productId : UUID.randomUUID().toString());
		request.setCode(requestBody.getCode());
		request.setName(requestBody.getName());
		request.setPurchasePrice(requestBody.getPurchasePrice());
		request.setSellingPrice(requestBody.getSellingPrice());
		request.setCategoryId(requestBody.getCategoryId());
		request.setFranchiseId(requestBody.getFranchiseId());
		return request;
	}

}
