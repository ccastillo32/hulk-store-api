package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterOutgoingInventoryRequest;

public class RegisterOutgoingInventoryRequestData {
	
	private static final String ID = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
	private static final BigDecimal UNIT_PRICE = new BigDecimal("5");
	private static final Integer QUANTITY = 1;
	private static final String OBSERVATION = "abc";
	private static final String PRODUCT_ID = ProductData.product1().getId();
	
	private RegisterOutgoingInventoryRequestData() {
	}
	
	public static RegisterOutgoingInventoryRequest nullRequest() {
		return null;
	}
	
	public static RegisterOutgoingInventoryRequest wihEmptyFields() {
		return new RegisterOutgoingInventoryRequest(null, null, null, null, null);
	}
	
	public static RegisterOutgoingInventoryRequest outgoingRequest() {
		return new RegisterOutgoingInventoryRequest(ID, PRODUCT_ID, QUANTITY, UNIT_PRICE, OBSERVATION);
	}
	
	public static RegisterOutgoingInventoryRequest withQuantityZero() {
		Integer quantity = 0;
		return new RegisterOutgoingInventoryRequest(ID, PRODUCT_ID, quantity, UNIT_PRICE, OBSERVATION);
	}

}
