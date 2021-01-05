package coop.tecso.exam.todo1.hulkstore.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductCodeAlreadyExistsException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductDoesNotExistException;

@Service

public class ProductService {

	private ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public void save(Product product) {
		repository.save(product);
	}
	
	public void checkIfCodeIsUnique(String productCode) {
		
		Optional<Product> optional = repository.findByCode(productCode);
		
		if(optional.isPresent()) {
			throw new ProductCodeAlreadyExistsException(productCode);
		}
		
	}
	
	public void checkIfProductExists(String productId) {
		
		Optional<Product> optional = repository.findById(productId);
		
		if(!optional.isPresent()) {
			throw new ProductDoesNotExistException(productId);
		}
		
	}
	
	public List<Product> findAllProducts() {
		return repository.findAll();
	}
	
}