package pl.bgnat.inventory.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bgnat.inventory.dto.InventoryResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
	private final InventoryRepository inventoryRepository;
	private final InventoryDtoMapper inventoryDtoMapper;

	@Transactional(readOnly = true)
	public List<InventoryResponseDto> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventoryDtoMapper)
				.collect(Collectors.toList());
	}
}
