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
	
	public void checkIfCodeIsAvailable(String productId, String productCode) {
		
		Optional<Product> optional = repository.findByCode(productCode);
		
		if(productId != null && optional.isPresent() && !(optional.get().getId().equals(productId))) {
			throw new ProductCodeAlreadyExistsException(productCode);
		}
		
		if(productId == null && optional.isPresent()) {
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
	
	public Product findById(String id) {
		Optional<Product> optional = repository.findById(id);
		
		if(!optional.isPresent()) {
			throw new ProductDoesNotExistException(id);
		}
		
		return optional.get();
	}
	
}
