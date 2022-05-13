package com.example.e3stpavel.knorders.controller;

import com.example.e3stpavel.knorders.assembler.OrderAssembler;
import com.example.e3stpavel.knorders.dao.OrderRepository;
import com.example.e3stpavel.knorders.entity.Order;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderRepository orderRepository;

    private OrderAssembler orderAssembler;

    @Autowired
    private void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    private void setOrderAssembler(OrderAssembler orderAssembler) {
        this.orderAssembler = orderAssembler;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Order>> all() {
        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @SneakyThrows
    @GetMapping(params = {"isoDate"})
    public CollectionModel<EntityModel<Order>> all(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String isoDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = simpleDateFormat.parse(isoDate);

        List<EntityModel<Order>> orders = orderRepository.findAllBySubmissionDate(date).stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all(isoDate)).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody Order order) {
        EntityModel<Order> orderEntityModel = orderAssembler.toModel(orderRepository.save(order));

        return ResponseEntity
                .created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(orderEntityModel);
    }
}
