package com.geranium.gift.repository;

import com.geranium.gift.model.entity.GiftImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftImageRepository extends JpaRepository<GiftImage, Long> {
}