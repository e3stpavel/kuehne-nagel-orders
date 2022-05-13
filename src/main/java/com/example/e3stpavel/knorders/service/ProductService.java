package com.example.e3stpavel.knorders.service;

import com.example.e3stpavel.knorders.assembler.ProductAssembler;
import com.example.e3stpavel.knorders.controller.ProductController;
import com.example.e3stpavel.knorders.dao.ProductRepository;
import com.example.e3stpavel.knorders.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService implements BasicService<Product> {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductAssembler productAssembler;

    @Autowired
    private void setProductAssembler(ProductAssembler productAssembler) {
        this.productAssembler = productAssembler;
    }

    @Override
    public CollectionModel<EntityModel<Product>> getAll() {
        List<EntityModel<Product>> products = productRepository.findAll().stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @Override
    public EntityModel<Product> add(Product product) {
        return productAssembler.toModel(productRepository.save(product));
    }
}
