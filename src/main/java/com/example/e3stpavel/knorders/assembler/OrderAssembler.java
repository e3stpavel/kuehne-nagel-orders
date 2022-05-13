package com.example.e3stpavel.knorders.assembler;

import com.example.e3stpavel.knorders.controller.OrderController;
import com.example.e3stpavel.knorders.entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(OrderController.class).all()).withRel("orders"),
                linkTo(methodOn(OrderController.class).all()).withRel("orders by submission date"))
        ;
    }
}
