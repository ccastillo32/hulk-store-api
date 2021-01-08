package coop.tecso.exam.todo1.hulkstore.application.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import coop.tecso.exam.todo1.hulkstore.domain.model.Product;

public class ProductData {
	
	private ProductData() {
	}
	
	public static List<Product> allProducts() {

		return Arrays.asList(product1(), product2());
		
	}
	
	public static Product product1() {
		return Product.of(
				"dc3029fc-ffd2-4d2b-9c2f-6c9c01ef4040", 
				"001", 
				"Product 1", 
				new BigDecimal("100"), 
				new BigDecimal("200"), 
				CategoryData.toys().getId(), 
				FranchiseData.dcComics().getId()
		);
	}
	
	public static Product product2() {
		return Product.of(
				"2f01b3e3-0424-4338-8c06-1cb54def3b77", 
				"002", 
				"Product 2", 
				new BigDecimal("100"), 
				new BigDecimal("200"), 
				CategoryData.toys().getId(), 
				FranchiseData.dcComics().getId()
		);
	}

}
