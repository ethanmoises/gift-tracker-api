package com.geranium.gift.mapper;

import com.geranium.gift.model.dto.GiftRequestDTO;
import com.geranium.gift.model.dto.GiftResponseDTO;
import com.geranium.gift.model.entity.Gift;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = GiftImageMapper.class
)
public interface GiftMapper {

    Gift toEntity(GiftRequestDTO dto);

    GiftResponseDTO toResponseDTO(Gift gift);

    void updateEntity(GiftRequestDTO dto, @MappingTarget Gift gift);
}