package com.example.e3stpavel.knorders.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface BasicService<T> {
    public abstract CollectionModel<EntityModel<T>> getAll();

    public abstract EntityModel<T> getOne(int id);

    public abstract EntityModel<T> add(T object);
    // here we can add more CRUD operations
}
