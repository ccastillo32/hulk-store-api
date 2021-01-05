package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateInventoryMovementRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.CreateInventoryMovementService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.CreateInventoryMovementHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.CreateInventoryMovementResponse;

@RestController

public class CreateInventoryMovementController {
	
	private CreateInventoryMovementService service;

	public CreateInventoryMovementController(CreateInventoryMovementService service) {
		this.service = service;
	}
	
	@PostMapping("/api/movements/{productId}")
	public ResponseEntity<CreateInventoryMovementResponse> hanldeRequest(@PathVariable String productId, @RequestBody CreateInventoryMovementHttpRequest requestBody) {
		
		CreateInventoryMovementRequest request = toRequest(productId, requestBody);
		
		service.execute(request);
		
		CreateInventoryMovementResponse responseBody = CreateInventoryMovementResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}
	
	private CreateInventoryMovementRequest toRequest(String productId, CreateInventoryMovementHttpRequest requestBody) {
		return CreateInventoryMovementRequest.of(
					UUID.randomUUID().toString(),
					productId, 
					requestBody.getType(), 
					requestBody.getQuantity(), 
					requestBody.getUnitPrice(), 
					requestBody.getObservation()
		);
	}

}
