package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterIncomingInventoryRequest;

public class RegisterIncomingInventoryRequestData {
	
	private static final String ID = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
	private static final BigDecimal UNIT_PRICE = new BigDecimal("5");
	private static final Integer QUANTITY = 1;
	private static final String OBSERVATION = "abc";
	private static final String PRODUCT_ID = ProductData.product1().getId();
	
	private RegisterIncomingInventoryRequestData() {
	}
	
	public static RegisterIncomingInventoryRequest incomingRequest() {
		return new RegisterIncomingInventoryRequest(ID, PRODUCT_ID, QUANTITY, UNIT_PRICE, OBSERVATION);
	}
	
	public static RegisterIncomingInventoryRequest withQuantityZero() {
		Integer quantity = 0;
		return new RegisterIncomingInventoryRequest(ID, PRODUCT_ID, quantity, UNIT_PRICE, OBSERVATION);
	}

}
