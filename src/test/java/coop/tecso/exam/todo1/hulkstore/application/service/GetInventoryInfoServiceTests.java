package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import coop.tecso.exam.todo1.hulkstore.application.data.MovementData;
import coop.tecso.exam.todo1.hulkstore.application.data.ProductData;
import coop.tecso.exam.todo1.hulkstore.application.dto.InventoryInfoDto;
import coop.tecso.exam.todo1.hulkstore.application.request.FilterOptions;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;

@ExtendWith(MockitoExtension.class)

final class GetInventoryInfoServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private MovementService movementService;
	
	private ProductService productService;
	
	private GetInventoryInfoService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		productService = new ProductService(productRepository);
		service = new GetInventoryInfoService(movementService, productService);
	}
	
	@Test
	@DisplayName("If there are not products should get no info")
	void shouldGetNoInfo() {
		
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
		
		List<InventoryInfoDto> items = service.execute(noFilter());
		
		assertEquals(0, items.size());
		
	}
	
	@Test
	@DisplayName("Should summarize movements and get total quantities on inventory")
	void shouldGetSomeInfo() {
		
		String productId = ProductData.product1().getId();
		mockProducts(productId);
		mockMovements(productId);
		
		List<InventoryInfoDto> items = service.execute(noFilter());
		
		InventoryInfoDto info = items.get(0);
		
		assertEquals(1, items.size());
		
		assertEquals(20, info.getIncomings());
		
		assertEquals(4, info.getOutgoings());
		
		assertEquals(16, info.getAvailableItems());
	}
	
	private void mockProducts(String productId) {

		List<Product> products = Arrays.asList(ProductData.product1());
		
		Mockito.when(productRepository.findAll()).thenReturn(products);
	}
	
	private void mockMovements(String productId) {

		Mockito.when(movementRepository.findAll()).thenReturn(MovementData.movementsByProduct(productId));
		
	}
	
	private FilterOptions noFilter() {
		return new FilterOptions();
	}
	
}
