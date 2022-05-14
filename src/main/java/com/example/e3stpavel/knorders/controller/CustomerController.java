package com.example.e3stpavel.knorders.controller;

import com.example.e3stpavel.knorders.entity.Customer;
import com.example.e3stpavel.knorders.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    private void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerService.getOne(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody Customer customer) {
        EntityModel<Customer> customerEntityModel = customerService.add(customer);

        return ResponseEntity
                .created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(customerEntityModel);
    }
}
