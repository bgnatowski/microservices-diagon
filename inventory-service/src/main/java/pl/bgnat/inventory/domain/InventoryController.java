package pl.bgnat.inventory.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
class InventoryController {
	private final InventoryService inventoryService;

	@GetMapping("/{skuCode}")
	@ResponseStatus(HttpStatus.OK)
	boolean isInStock(@PathVariable("skuCode") String skuCode){
		return inventoryService.isInStock(skuCode);
	}
}
