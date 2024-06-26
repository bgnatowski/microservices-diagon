package pl.bgnat.product.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.product.dto.ProductDtoResponse;
import pl.bgnat.product.dto.ProductRequest;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	@Value("${server.port}")
	private int port;
	private final ProductService productService;
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	void createProduct(@RequestBody ProductRequest productRequest){
		productService.createProduct(productRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<List<ProductDtoResponse>> getAllProducts(){
		List<ProductDtoResponse> allProducts = productService.getAllProducts();
		return ResponseEntity.ok(allProducts);
	}
	@GetMapping("/status")
	String checkStatus(){
		return String.format("Product-service is working on port: %d!", port);
	}
}
