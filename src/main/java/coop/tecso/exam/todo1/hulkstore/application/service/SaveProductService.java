package coop.tecso.exam.todo1.hulkstore.application.service;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.request.SaveProductRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.model.ProductBuilder;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class SaveProductService {
	
	private ProductService productService;
	private CategoryService categoryService;
	private FranchiseService franchiseService;
	
	public SaveProductService(ProductService productService, CategoryService categoryService, FranchiseService franchiseService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.franchiseService = franchiseService;
	}
	
	public void execute(SaveProductRequest request, boolean isANewProduct) {
		
		FieldValidator.notNull(request, "SaveProductRequest");
		
		Product product = ProductBuilder.newInstance()
				                        .id(request.getId())
				                        .code(request.getCode())
				                        .name(request.getName())
				                        .purchasePrice(request.getPurchasePrice())
				                        .sellingPrice(request.getSellingPrice())
				                        .categoryId(request.getCategoryId())
				                        .franchiseId(request.getFranchiseId())
				                        .build();
		
		if(isANewProduct) {
			productService.checkIfCodeIsAvailable(null, product.getCode());
		}
		
		if(!isANewProduct) {
			productService.checkIfProductExists(request.getId());
			productService.checkIfCodeIsAvailable(product.getId(), product.getCode());
		}
		
		categoryService.checkIfCategoryExists(product.getCategoryId());
		
		franchiseService.checkIfFranchiseExists(product.getFranchiseId());
		
		productService.save(product);
		
	}
	
}
