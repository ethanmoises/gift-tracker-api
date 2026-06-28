package com.geranium.gift.repository.specification;

import com.geranium.gift.model.entity.Gift;
import com.geranium.gift.model.enums.GiftStatus;
import org.springframework.data.jpa.domain.Specification;

public class GiftSpecification {

    public static Specification<Gift> ideaContains(String idea) {
        return (root, query, cb) -> {
            if (idea == null || idea.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("idea")),
                    "%" + idea.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Gift> hasStatus(GiftStatus status) {
        return (root, query, cb) -> {
            if (status == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Gift> priceBetween(Double min, Double max) {
        return (root, query, cb) -> {

            if (min == null && max == null) {
                return cb.conjunction();
            }

            if (min == null) {
                return cb.lessThanOrEqualTo(root.get("price"), max);
            }

            if (max == null) {
                return cb.greaterThanOrEqualTo(root.get("price"), min);
            }

            return cb.between(root.get("price"), min, max);
        };
    }

    public static Specification<Gift> belongsToPerson(String personName) {
        return (root, query, cb) -> {

            if (personName == null || personName.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.join("person").get("name")),
                    "%" + personName.toLowerCase() + "%"
            );
        };
    }
}