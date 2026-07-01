package com.geranium.gift.repository.specification;

import com.geranium.gift.model.entity.Person;
import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.model.enums.Relationship;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonSpecification {

    public static Specification<Person> hasName(String name) {
        return (root, query, cb) -> {

            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Person> hasOccasion(Occasion occasion) {
        return (root, query, cb) -> {

            if (occasion == null) {
                return cb.conjunction();
            }

            return cb.equal(
                    root.get("occasion"),
                    occasion
            );
        };
    }

    public static Specification<Person> hasRelationship(Relationship relationship) {
        return (root, query, cb) -> {

            if (relationship == null) {
                return cb.conjunction();
            }

            return cb.equal(
                    root.get("relationship"),
                    relationship
            );
        };
    }

    public static Specification<Person> hasEventDate(LocalDate eventDate) {
        return (root, query, cb) -> {

            if (eventDate == null) {
                return cb.conjunction();
            }

            return cb.equal(
                    root.get("eventDate"),
                    eventDate
            );
        };
    }

    public static Specification<Person> hasKeyword(String keyword) {
        return (root, query, cb) -> {

            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            String search = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                    cb.like(
                            cb.lower(root.get("name")),
                            search
                    ),
                    cb.like(
                            cb.lower(root.get("notes")),
                            search
                    ),
                    cb.like(
                            cb.lower(root.get("occasion").as(String.class)),
                            search
                    )
            );
        };
    }
}