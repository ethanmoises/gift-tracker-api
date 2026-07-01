package com.geranium.gift.model.dto;

import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.model.enums.Relationship;

import java.time.LocalDate;
import java.util.List;

public record PersonRequestDTO(

        String name,

        Occasion occasion,

        LocalDate eventDate,

        Relationship relationship,

        String notes,

        String imageUrl,

        List<GiftRequestDTO> gifts

) {
}