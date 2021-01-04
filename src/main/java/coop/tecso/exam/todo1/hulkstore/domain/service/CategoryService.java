package coop.tecso.exam.todo1.hulkstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;

@Service

public class CategoryService {

	private CategoryRepository repository;

	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}
	
	public List<Category> findAllCategories() {
		return repository.findAll();
	}
	
}
