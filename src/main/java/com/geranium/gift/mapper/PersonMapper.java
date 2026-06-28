package com.geranium.gift.mapper;

import com.geranium.gift.model.dto.PersonRequestDTO;
import com.geranium.gift.model.dto.PersonResponseDTO;
import com.geranium.gift.model.entity.Person;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = GiftMapper.class)
public interface PersonMapper {

    Person toEntity(PersonRequestDTO dto);

    PersonResponseDTO toResponseDTO(Person person);

    @AfterMapping
    default void setPersonOnGifts(@MappingTarget Person person) {

        if (person.getGifts() != null) {
            person.getGifts()
                    .forEach(gift -> gift.setPerson(person));
        }
    }
}