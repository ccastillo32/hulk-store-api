package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import coop.tecso.exam.todo1.hulkstore.application.data.CategoryData;
import coop.tecso.exam.todo1.hulkstore.application.data.FranchiseData;
import coop.tecso.exam.todo1.hulkstore.application.data.ProductData;
import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.application.request.FilterOptions;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;

/**
 * Use case: It gets a list of all the products stored in the database
 * @author Cristian
 *
 */

@ExtendWith(MockitoExtension.class)

final class FindAllProductsServiceTests {

	@Mock
	private FranchiseRepository franchiseRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;
	
	private FindAllProductsService service;
	
	@BeforeEach
	public void setUp() {
		productService = new ProductService(productRepository);
		categoryService = new CategoryService(categoryRepository);
		franchiseService = new FranchiseService(franchiseRepository);
		service = new FindAllProductsService(productService, categoryService, franchiseService);
	}
	
	@Test
	@DisplayName("It should get an empty list of products")
	void shouldGetAnEmptyListOfProducts() {
		
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
		
		List<ProductDto> allProducts = service.execute(noFilter());
		
		assertNotNull(allProducts);
		assertEquals(0, allProducts.size());
		
	}
	
	@Test
	@DisplayName("It should get an non-empty list of products")
	void shouldGetAnNonEmptyListOfProducts() {
		
		Mockito.when(categoryRepository.findAll()).thenReturn(CategoryData.allCategories());
		Mockito.when(franchiseRepository.findAll()).thenReturn(FranchiseData.allFranchises());
		Mockito.when(productRepository.findAll()).thenReturn(ProductData.allProducts());
		
		List<ProductDto> allProducts = service.execute(noFilter());
		
		assertNotNull(allProducts);
		assertEquals(2, allProducts.size());
		
	}
	
	private FilterOptions noFilter() {
		return new FilterOptions();
	}
	
}
