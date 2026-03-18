package com.robson.repository;

import com.robson.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public PanacheQuery<User> findByName(String name) {
        return find("LOWER(name) LIKE LOWER(?1)", "%" + name + "%");
    }
}