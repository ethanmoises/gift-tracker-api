package com.geranium.gift.mapper;

import com.geranium.gift.model.dto.GiftRequestDTO;
import com.geranium.gift.model.dto.GiftResponseDTO;
import com.geranium.gift.model.entity.Gift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftMapper {

    Gift toEntity(GiftRequestDTO dto);

    GiftResponseDTO toResponseDTO(Gift gift);
}