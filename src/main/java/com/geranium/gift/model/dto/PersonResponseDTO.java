package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.Occasion;

import java.util.List;

public record PersonResponseDTO(
        Long id,
        String name,
        Occasion occasion,
        String notes,
        List<GiftResponseDTO> gifts
) {
}