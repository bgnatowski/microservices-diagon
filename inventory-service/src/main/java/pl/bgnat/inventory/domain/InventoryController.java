package pl.bgnat.inventory.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.inventory.dto.InventoryDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
	@Value("${server.port}")
	private int port;
	private final InventoryService inventoryService;


	// http://localhost:8083/api/v1/inventory/iphone_13,skuCode=iphone13_red
	// http://localhost:8181/api/v1/inventory/iphone_13,skuCode=iphone13_red
	// http://localhost:8083/api/v1/inventory?skuCode=iphone_13&skuCode=iphone13_red
	// http://localhost:8181/api/v1/inventory?skuCode=iphone_13&skuCode=iphone13_red
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<InventoryDto>> isInStock(@RequestParam("skuCode") List<String> skuCodes){
		return ResponseEntity.ok(inventoryService.isInStock(skuCodes));
	}

	// http://localhost:8083/api/v1/inventory/status
	// http://localhost:8181/api/v1/inventory/status
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<String> checkStatus(){
		return ResponseEntity.ok(String.format("Inventory-service is working on port: %d!", port));
	}
}
