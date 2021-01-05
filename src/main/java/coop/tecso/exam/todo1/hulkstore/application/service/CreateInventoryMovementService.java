package coop.tecso.exam.todo1.hulkstore.application.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateInventoryMovementRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.NotAValidMovementTypeException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class CreateInventoryMovementService {

	private MovementService movementService;
	private ProductService productService;
	
	public CreateInventoryMovementService(MovementService movementService, ProductService productService) {
		this.movementService = movementService;
		this.productService = productService;
	}

	public void execute(CreateInventoryMovementRequest request) {
		
		FieldValidator.notNull(request, "CreateInventoryMovementRequest");

		String movementType = request.getType();
		String productId = request.getProductId();
		
		checkIfItsAValidMovementType(movementType);

		checkIfItsAValidProduct(productId);
		
		// Check quantity and price
		
		if(isAnIncomingInventoryMovement(movementType)) {
			Movement movement = toMovement(request);
			movementService.saveMovement(movement);
		}

	}
	
	private boolean isAnIncomingInventoryMovement(String type) {
		return MovementType.INCOMINGS.name().equals(type);
	}
	
	private void checkIfItsAValidMovementType(String type) {
		List<String> types = Arrays.asList(MovementType.values()).stream().map(t -> t.name()).collect(Collectors.toList());
		boolean isAValidMovementType = types.contains(type);
		if(!isAValidMovementType) {
			throw new NotAValidMovementTypeException(type);
		}
	}
	
	private void checkIfItsAValidProduct(String productId) {
		productService.checkIfProductExists(productId);
	}
	
	private Movement toMovement(CreateInventoryMovementRequest request) {

		MovementType type = MovementType.valueOf(request.getType());
		
		return Movement.of(
				request.getId(), 
				request.getProductId(), 
				type, 
				request.getQuantity(), 
				request.getUnitPrice(), 
				request.getObservation(), 
				LocalDateTime.now()
		);
	}
	
}
