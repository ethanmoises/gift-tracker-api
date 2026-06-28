package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.GiftStatus;

public record GiftRequestDTO(
        String idea,
        double price,
        String link,
        GiftStatus status
) {
}