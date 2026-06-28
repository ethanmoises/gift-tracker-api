package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.GiftStatus;

public record GiftResponseDTO(
        Long id,
        String idea,
        double price,
        String link,
        GiftStatus status
) {
}