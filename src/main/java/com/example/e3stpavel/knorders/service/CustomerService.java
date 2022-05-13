package com.example.e3stpavel.knorders.service;

import com.example.e3stpavel.knorders.assembler.CustomerAssembler;
import com.example.e3stpavel.knorders.controller.CustomerController;
import com.example.e3stpavel.knorders.dao.CustomerRepository;
import com.example.e3stpavel.knorders.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerService implements BasicService<Customer> {
    private CustomerRepository customerRepository;

    private CustomerAssembler customerAssembler;

    @Autowired
    private void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private void setCustomerAssembler(CustomerAssembler customerAssembler) {
        this.customerAssembler = customerAssembler;
    }

    @Override
    public CollectionModel<EntityModel<Customer>> getAll() {
        List<EntityModel<Customer>> customers = customerRepository.findAll().stream()
                .map(customerAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @Override
    public EntityModel<Customer> add(Customer customer) {
        return customerAssembler.toModel(customerRepository.save(customer));
    }
}
