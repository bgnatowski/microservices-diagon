package pl.bgnat.product.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bgnat.product.dto.ProductDtoResponse;
import pl.bgnat.product.dto.ProductRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductDtoMapper productDtoMapper;
	public void createProduct(ProductRequest productRequest){
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		productRepository.save(product);
		log.info("Product with id: {} is saved", product.getId());
	}

	public List<ProductDtoResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		return products.stream()
				.map(productDtoMapper)
				.collect(Collectors.toList());
	}
}
