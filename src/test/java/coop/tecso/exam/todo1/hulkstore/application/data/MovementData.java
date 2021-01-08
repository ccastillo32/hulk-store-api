package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
	
}
