package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.GiftStatus;
import com.geranium.gift.model.enums.Priority;

import java.math.BigDecimal;
import java.util.List;

public record GiftResponseDTO(

        Long id,

        String idea,

        BigDecimal price,

        BigDecimal budget,

        Integer quantity,

        Priority priority,

        String notes,

        String link,

        GiftStatus status,

        List<GiftImageResponseDTO> images

) {
}