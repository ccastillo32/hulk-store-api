package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import coop.tecso.exam.todo1.hulkstore.application.dto.CategoryDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;

@ExtendWith(MockitoExtension.class)

final class FindAllCategoriesServiceTests {

	@Mock
	private CategoryRepository categoryRepository;
	
	private CategoryService categoryService;
	
	private FindAllCategoriesService service;
	
	@BeforeEach
	public void setUp() {
		categoryService = new CategoryService(categoryRepository);
		service = new FindAllCategoriesService(categoryService);
	}
	
	@Test
	@DisplayName("Should get a empty list of categories")
	void shouldGetANonEmptyList() {
		
		int expectedCategories = 2;
		
		mockCategoriesInDatabaseWith(CategoryData.allCategories());
		
		List<CategoryDto> categories = service.execute();
		
		assertNotNull(categories);
		assertEquals(expectedCategories, categories.size());
		
	}
	
	@Test
	@DisplayName("Should get an empty list of categories")
	void shouldGetAnEmptyList() {
		
		int expectedCategories = 0;
		
		mockCategoriesInDatabaseWith(Collections.emptyList());
		
		List<CategoryDto> categories = service.execute();
		
		assertNotNull(categories);
		assertEquals(expectedCategories, categories.size());
		
	}
	
	private void mockCategoriesInDatabaseWith(List<Category> mock) {
		Mockito.when(categoryRepository.findAll()).thenReturn(mock);
	}

}
