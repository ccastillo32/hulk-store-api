package coop.tecso.exam.todo1.hulkstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;

@Service

public class FranchiseService {

	private FranchiseRepository repository;

	public FranchiseService(FranchiseRepository repository) {
		this.repository = repository;
	}
	
	public List<Franchise> findAllFranchises() {
		return repository.findAll();
	}
	
}
