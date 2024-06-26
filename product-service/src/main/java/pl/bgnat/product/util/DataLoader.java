package pl.bgnat.product.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bgnat.product.domain.Product;
import pl.bgnat.product.domain.ProductRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() < 1) {
            Product product = new Product();
            product.setName("iPhone 13");
            product.setDescription("iPhone 13");
            product.setPrice(BigDecimal.valueOf(1000));

            productRepository.save(product);
        }
    }
}
