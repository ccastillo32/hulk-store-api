package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import coop.tecso.exam.todo1.hulkstore.application.data.CategoryData;
import coop.tecso.exam.todo1.hulkstore.application.data.CreateProductRequestData;
import coop.tecso.exam.todo1.hulkstore.application.data.FranchiseData;
import coop.tecso.exam.todo1.hulkstore.application.request.CreateProductRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.model.ProductBuilder;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.CategoryDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.FranchiseDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductCodeAlreadyExistsException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ExtendWith(MockitoExtension.class)

final class CreateProductServiceTests {

	@Mock
	private FranchiseRepository franchiseRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;
	
	private CreateProductService service;
	
	@BeforeEach
	public void setUp() {
		productService = new ProductService(productRepository);
		categoryService = new CategoryService(categoryRepository);
		franchiseService = new FranchiseService(franchiseRepository);
		service = new CreateProductService(productService, categoryService, franchiseService);
	}
	
	@Test
	@DisplayName("Cannot invoke service with a null parameter")
	void cannotInvokeServiceWithNullParameter() {
		
		assertThrows(InvalidFieldException.class, () -> service.execute(CreateProductRequestData.nullRequest()) );
		
	}
	
	@Test
	@DisplayName("Cannot create a product with null or empty fields.")
	void cannotCreateProductWithEmptyFields() {
		
		CreateProductRequest request = CreateProductRequestData.requestWithEmptyFields();
		
		assertThrows(InvalidFieldException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Create a valid product")
	void createAValidProduct() {

		CreateProductRequest request = CreateProductRequestData.validRequest();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(CategoryData.comics()));
		
		Mockito.when(franchiseRepository.findById(request.getFranchiseId())).thenReturn(Optional.of(FranchiseData.dcComics()));

		assertDoesNotThrow(() -> service.execute(request) ); 
		
	}
	
	@Test
	@DisplayName("Should not create a product if the category does not exist")
	void shouldNotCreateIfCategoryIsInvalid() {
		
		CreateProductRequest request = CreateProductRequestData.validRequest();
		
		String categoryId = request.getCategoryId();
		
		Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryDoesNotExistException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Should not create a product if the franchise does not exist")
	void shouldNotCreateIfFranchiseIsInvalid() {
		
		CreateProductRequest request = CreateProductRequestData.validRequest();
		
		String franchiseId = request.getFranchiseId();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(CategoryData.comics()));
		Mockito.when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.empty());
		
		assertThrows(FranchiseDoesNotExistException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Should not create a product if the CODE is already registered")
	void productCodeMustBeUnique() {
		
		CreateProductRequest request = CreateProductRequestData.validRequest();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(CategoryData.comics()));
		
		Mockito.when(franchiseRepository.findById(request.getFranchiseId())).thenReturn(Optional.of(FranchiseData.dcComics()));
		
		assertDoesNotThrow(() -> service.execute(request) ); 
		
		Product product = ProductBuilder.newInstance().id(request.getId())
				                                      .name(request.getName())
				                                      .code(request.getCode())
				                                      .categoryId(request.getCategoryId())
				                                      .franchiseId(request.getFranchiseId())
				                                      .purchasePrice(new BigDecimal("1"))
				                                      .sellingPrice(new BigDecimal("1"))
				                                      .build();
		
		Mockito.when(productRepository.findByCode(request.getCode())).thenReturn( Optional.of(product) );
		
		assertThrows(ProductCodeAlreadyExistsException.class, () -> service.execute(request) );
		
	}
	
}
