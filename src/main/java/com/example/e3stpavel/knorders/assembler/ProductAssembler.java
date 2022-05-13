package com.example.e3stpavel.knorders.assembler;

import com.example.e3stpavel.knorders.controller.ProductController;
import com.example.e3stpavel.knorders.entity.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductController.class).all()).withRel("products"));
    }
}
