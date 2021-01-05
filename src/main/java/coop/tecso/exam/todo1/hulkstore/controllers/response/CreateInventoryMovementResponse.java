package coop.tecso.exam.todo1.hulkstore.controllers.response;

public class CreateInventoryMovementResponse {
	
	private String movementId;

	public CreateInventoryMovementResponse(String movementId) {
		this.movementId = movementId;
	}
	
	public static CreateInventoryMovementResponse of(String movementId) {
		return new CreateInventoryMovementResponse(movementId);
	}

	public String getMovementId() {
		return movementId;
	}

}
