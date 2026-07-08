package com.geranium.gift.mapper;

import com.geranium.gift.model.dto.GiftImageResponseDTO;
import com.geranium.gift.model.entity.GiftImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftImageMapper {

    GiftImageResponseDTO toResponseDTO(GiftImage giftImage);

}