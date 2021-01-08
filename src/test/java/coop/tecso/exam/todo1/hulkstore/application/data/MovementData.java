package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;

import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;

public class MovementData {

	private MovementData() {
	}
	
	public static List<Movement> allMovements() {
		String id = "c26907bb-adf4-4160-96e0-20545a3543ef";
		String productId = ProductData.product1().getId();
		MovementType type = MovementType.INCOMINGS;
		Integer quantity = 10;
		BigDecimal price = new BigDecimal("3000");
		String observation = "Some observation";
		LocalDateTime createdAt = LocalDateTime.now();
		Movement movementOne = Movement.of(id, productId, type, quantity, price, observation, createdAt);
		
		return Arrays.asList(movementOne);
	}
	
	public static List<Movement> movementsByProduct(String productId) {
		
		Movement incomingMovement = Movement.of(
										"c26907bb-adf4-4160-96e0-20545a3543ef", 
										productId, 
										MovementType.INCOMINGS, 
										20, 
										new BigDecimal("30000"), 
										"", 
										LocalDateTime.now()
									);
		
		Movement outgoingMovement = Movement.of(
										"658de796-4a07-4f4d-986f-4cd60b1004f9", 
										productId, 
										MovementType.OUTGOINGS, 
										4, 
										new BigDecimal("34000"), 
										"", 
										LocalDateTime.now()
									);
		
		return Arrays.asList( incomingMovement, outgoingMovement );
		
	}
	
}
