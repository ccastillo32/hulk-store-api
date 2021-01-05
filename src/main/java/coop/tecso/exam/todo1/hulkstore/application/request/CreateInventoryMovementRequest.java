package coop.tecso.exam.todo1.hulkstore.application.request;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

public class CreateInventoryMovementRequest {
	
	private String id;
	
	private String productId;
	
	private String type;
	
	private Integer quantity;
	
	private BigDecimal unitPrice;
	
	private String observation;

	public CreateInventoryMovementRequest(String id, String productId, String type, Integer quantity, BigDecimal unitPrice,
			String observation) {
		
		FieldValidator.notEmpty("id", id);
		FieldValidator.notEmpty("productId", productId);
		FieldValidator.notEmpty("type", type);
		FieldValidator.notNull(quantity, "quantity");
		FieldValidator.notNull(unitPrice, "unitPrice");
		FieldValidator.notEmpty("observation", observation);
		
		FieldValidator.notZeroOrBelow(quantity, "quantity");
		
		this.id = id;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.observation = observation;
	}

	public static CreateInventoryMovementRequest of(String id, String productId, String type, Integer quantity, BigDecimal unitPrice,
			String observation) {
		return new CreateInventoryMovementRequest(id, productId, type, quantity, unitPrice, observation);
	}

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public String getType() {
		return type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public String getObservation() {
		return observation;
	}
	
}
