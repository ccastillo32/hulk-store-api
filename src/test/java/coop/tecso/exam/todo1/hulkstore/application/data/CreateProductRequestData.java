package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateProductRequest;

public class CreateProductRequestData {
	
	private CreateProductRequestData() {	
	}
	
	public static CreateProductRequest validRequest() {
		CreateProductRequest request = new CreateProductRequest();
		request.setId("443b5be9-9e8c-46b7-af0e-1810da29a0f4");
		request.setCode("C001XYZ");
		request.setName("Spiderman comic #001");
		request.setPurchasePrice(new BigDecimal("35898800"));
		request.setSellingPrice(new BigDecimal("40000000"));
		request.setCategoryId(CategoryData.comics().getId());
		request.setFranchiseId(FranchiseData.dcComics().getId());
		return request;
	}
	
	public static CreateProductRequest requestWithEmptyFields() {
		CreateProductRequest request = new CreateProductRequest();
		request.setId("");
		return request;
	}
	
	public static CreateProductRequest nullRequest() {
		return null;
	}

}
