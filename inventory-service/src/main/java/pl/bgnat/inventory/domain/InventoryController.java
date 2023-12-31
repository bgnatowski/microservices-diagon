package pl.bgnat.inventory.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.inventory.dto.InventoryDto;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
	private final InventoryService inventoryService;


	//http://localhost:8086/api/v1/inventory/skuCode=iphone-13&skuCode=iphone13-red
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<InventoryDto>> isInStock(@RequestParam("skuCode") List<String> skuCodes){
		return ResponseEntity.ok(inventoryService.isInStock(skuCodes));
	}
}
