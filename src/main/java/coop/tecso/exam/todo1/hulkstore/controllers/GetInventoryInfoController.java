package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.dto.InventoryInfoDto;
import coop.tecso.exam.todo1.hulkstore.application.service.GetInventoryInfoService;
import coop.tecso.exam.todo1.hulkstore.controllers.response.GetInventoryInfoResponse;

@RestController

public class GetInventoryInfoController {

	private GetInventoryInfoService service;

	public GetInventoryInfoController(GetInventoryInfoService service) {
		this.service = service;
	}
	
	@GetMapping("/api/inventory-info")
	public ResponseEntity<GetInventoryInfoResponse> handleRequest() {
		
		List<InventoryInfoDto> allData = service.execute();
		
		GetInventoryInfoResponse responseBody = GetInventoryInfoResponse.of(allData);
		
		return ResponseEntity.ok(responseBody);
		
	}
	
}
