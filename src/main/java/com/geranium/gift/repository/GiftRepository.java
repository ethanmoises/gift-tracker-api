package com.geranium.gift.repository;

import com.geranium.gift.model.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GiftRepository
        extends JpaRepository<Gift, Long>,
        JpaSpecificationExecutor<Gift> {
}