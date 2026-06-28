package com.geranium.gift.repository;

import com.geranium.gift.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository
        extends JpaRepository<Person, Long>,
        JpaSpecificationExecutor<Person> {
}