package coop.tecso.exam.todo1.hulkstore.domain.service;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;

@Service

public class MovementService {

	private MovementRepository repository;

	public MovementService(MovementRepository repository) {
		this.repository = repository;
	}

	public void saveMovement(Movement movement) {
		repository.save(movement);
	}
	
}
