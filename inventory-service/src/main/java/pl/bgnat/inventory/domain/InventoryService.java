package pl.bgnat.inventory.domain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bgnat.inventory.dto.InventoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	private final InventoryRepository inventoryRepository;
	private final InventoryDtoMapper inventoryDtoMapper;

	@Transactional(readOnly = true)
	@SneakyThrows
	public List<InventoryDto> isInStock(List<String> skuCode) {
//		log.info("Wait Started"); //tests
//		Thread.sleep(10000);
//		log.info("Wait Ended");
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventoryDtoMapper)
				.collect(Collectors.toList());
	}
}
