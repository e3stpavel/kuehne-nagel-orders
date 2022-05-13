package com.example.e3stpavel.knorders.assembler;

import com.example.e3stpavel.knorders.controller.CustomerController;
import com.example.e3stpavel.knorders.entity.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public EntityModel<Customer> toModel(Customer entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
    }
}
