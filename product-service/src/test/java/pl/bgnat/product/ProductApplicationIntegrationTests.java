package pl.bgnat.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.bgnat.product.domain.Product;
import pl.bgnat.product.domain.ProductRepository;
import pl.bgnat.product.dto.ProductRequest;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductApplicationIntegrationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.7");

	static {
		mongoDBContainer.start();
	}

	@BeforeEach
	void setUp() {
		productRepository.deleteAll();
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void canStartMongoDB(){
		Assertions.assertTrue(mongoDBContainer.isRunning());
		Assertions.assertTrue(mongoDBContainer.isCreated());
	}

	@SneakyThrows
	@Test
	void shouldCreateProduct() {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@SneakyThrows
	@Test
	void shouldFindAllProducts() {
		Product product = Product.builder()
				.id("64affcbc1c233a19cb1d5cd3")
				.name("iPhone 13")
				.description("iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
		productRepository.save(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product"))
				.andExpect(status().isOk());
		Assertions.assertEquals(1, productRepository.findAll().size());
		Assertions.assertEquals(product, productRepository.findById(product.getId()).get());
	}


	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iPhone 13")
				.description("iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}


}
