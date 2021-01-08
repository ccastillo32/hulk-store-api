package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import coop.tecso.exam.todo1.hulkstore.application.dto.MovementDto;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;

@ExtendWith(MockitoExtension.class)

final class FindAllMovementsServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	private MovementService movementService;
	
	private FindAllMovementsService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		service = new FindAllMovementsService(movementService);
	}
	
	@Test
	@DisplayName("Should get a non empty list of movements")
	void shouldGetANonEmptyList() {
		
		Mockito.when(movementRepository.findAll()).thenReturn(MovementData.allMovements());
		
		int expectedMovements = 1;
		
		String productId = null;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}
	
	@Test
	@DisplayName("Should get an empty list of movements")
	void shouldGetAnEmptyList() {
		
		Mockito.when(movementRepository.findAll()).thenReturn(Collections.emptyList());
		
		int expectedMovements = 0;
		
		String productId = null;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}
	
	@Test
	@DisplayName("Should get all the movements by product id")
	void shouldGetAllTheMovementsByProduct() {
		
		String productId = ProductData.product1().getId();
		
		Mockito.when(movementRepository.findByProductId(productId)).thenReturn(MovementData.allMovements());
		
		int expectedMovements = 1;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}

}
