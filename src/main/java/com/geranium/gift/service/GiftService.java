package com.geranium.gift.service;

import com.geranium.gift.model.dto.GiftRequestDTO;
import com.geranium.gift.model.dto.GiftResponseDTO;
import com.geranium.gift.model.enums.GiftStatus;
import com.geranium.gift.model.enums.Priority;

import java.math.BigDecimal;
import java.util.List;

public interface GiftService {

    GiftResponseDTO addGift(Long personId,
                            GiftRequestDTO request);

    GiftResponseDTO getGiftById(Long id);

    List<GiftResponseDTO> getAllGifts(
            String idea,
            GiftStatus status,
            Priority priority,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            BigDecimal minBudget,
            BigDecimal maxBudget,
            String personName
    );

    GiftResponseDTO updateGift(Long id,
                               GiftRequestDTO request);

    void deleteGift(Long id);
}