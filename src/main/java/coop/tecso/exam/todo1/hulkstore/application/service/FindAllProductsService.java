package coop.tecso.exam.todo1.hulkstore.application.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;

@Service

public class FindAllProductsService {

	private ProductService productService;
	private CategoryService categoryService;
	private FranchiseService franchiseService;
	
	public FindAllProductsService(ProductService productService, CategoryService categoryService,
			FranchiseService franchiseService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.franchiseService = franchiseService;
	}
	
	public List<ProductDto> execute() {
		
		List<Product> allProducts = productService.findAllProducts();
		
		if(allProducts.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Category> allCategories = categoryService.findAllCategories();
		List<Franchise> allFranchises = franchiseService.findAllFranchises();
		
		return allProducts.stream()
		           .map(product -> {
		        	   
		        	   Category category = allCategories.stream()
		        			                            .filter(c -> c.getId().equals(product.getCategoryId()))
		        			                            .findFirst()
		        			                            .get();
		        	   
		        	   Franchise franchise = allFranchises.stream()
		        			                              .filter(f -> f.getId().equals(product.getFranchiseId()))
		        			                              .findFirst()
		        			                              .get();
		        	   
		        	   return ProductDto.from(product, category, franchise);
		        	   
		           })
		           .collect(Collectors.toList());
		
	}
	
}
