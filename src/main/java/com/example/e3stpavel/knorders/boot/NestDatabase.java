package com.example.e3stpavel.knorders.boot;

import com.example.e3stpavel.knorders.dao.ProductRepository;
import com.example.e3stpavel.knorders.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class NestDatabase implements CommandLineRunner {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> HERE");
        // TODO: not adding to db

        if (productRepository.count() != 0)
            return;

        Product product = new Product() {
            {
                setName("My Product");
                setSkuCode("8373h3h3");
                setUnitPrice(9.4f);
            }
        };

        productRepository.save(product);
    }
}
