package coop.tecso.exam.todo1.hulkstore.controllers.request;

import java.math.BigDecimal;

public class CreateInventoryMovementHttpRequest {

	private String type;
	
	private Integer quantity;
	
	private BigDecimal unitPrice;
	
	private String observation;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}
