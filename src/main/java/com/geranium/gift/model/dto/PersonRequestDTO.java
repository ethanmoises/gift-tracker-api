package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.Occasion;

import java.util.List;

public record PersonRequestDTO(
        String name,
        Occasion occasion,
        String notes,
        List<GiftRequestDTO> gifts
) {
}