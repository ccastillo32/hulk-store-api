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

import coop.tecso.exam.todo1.hulkstore.application.request.CreateInventoryMovementRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.NotAValidMovementTypeException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ExtendWith(MockitoExtension.class)

final class CreateInventoryMovementServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private MovementService movementService;
	
	private ProductService productService;
	
	private CreateInventoryMovementService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		productService = new ProductService(productRepository);
		service = new CreateInventoryMovementService(movementService, productService);
	}
	
	@Test
	@DisplayName("Cannot allow to invoke service with a null parameter")
	void cannotInvokeServiceWithNullParameter() {
		
		assertThrows(InvalidFieldException.class, () -> service.execute(null) );
		
	}
	
	@Test
	@DisplayName("Cannot create a movement with null or empty fields.")
	void cannotCreateAMovementWithEmptyFields() {
		
		assertThrows(InvalidFieldException.class, () -> new CreateInventoryMovementRequest(null, null, null, null, null, null) );
		
	}
	
	@Test
	@DisplayName("Cannot create a movement if the type is not 'INCOMINGS' or 'OUTGOINGS'")
	void cannotCreateMovementWithAnUnknwonType() {
		
		String id = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String type = "WHATEVER";
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 1;
		BigDecimal unitPrice = new BigDecimal("5");
		CreateInventoryMovementRequest request = new CreateInventoryMovementRequest(id, productId, type, quantity, unitPrice, "");
		
		assertThrows(NotAValidMovementTypeException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Cannot create a movement if the Product Id is unknown")
	void cannotCreateMovementForAnUnknwonProduct() {
		
		String id = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String type = "INCOMINGS";
		String randomProductId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 1;
		BigDecimal unitPrice = new BigDecimal("5");
		CreateInventoryMovementRequest request = new CreateInventoryMovementRequest(id, randomProductId, type, quantity, unitPrice, "");
		
		Mockito.when(productRepository.findById(randomProductId)).thenReturn(Optional.empty());
		
		assertThrows(ProductDoesNotExistException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Cannot create a movement if the quantity is zero or below")
	void cannotCreateMovementWithQuantityZero() {
		
		String id = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String type = "INCOMINGS";
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 0;
		BigDecimal unitPrice = new BigDecimal("5");
		
		assertThrows(InvalidFieldException.class, () -> new CreateInventoryMovementRequest(id, productId, type, quantity, unitPrice, ""));
		
	}
	
	@Test
	@DisplayName("should create a valid incoming inventory movement")
	void createAValidIncomingInventoryMovement() {

		String id = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String type = MovementType.INCOMINGS.name();
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 5;
		BigDecimal unitPrice = new BigDecimal("5000");
		
		Category category = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		Franchise franchise = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Product product = Product.of(productId, "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), category.getId(), franchise.getId());
		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
		
		CreateInventoryMovementRequest request = CreateInventoryMovementRequest.of(id, productId, type, quantity, unitPrice, "");
		
		assertDoesNotThrow( () -> service.execute(request) );
		
	}
	
	
}
