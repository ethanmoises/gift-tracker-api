package com.geranium.gift.service;

import com.geranium.gift.model.dto.PersonRequestDTO;
import com.geranium.gift.model.dto.PersonResponseDTO;
import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.model.enums.Relationship;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {

    PersonResponseDTO createPerson(PersonRequestDTO request);

    PersonResponseDTO getPersonById(Long id);

    List<PersonResponseDTO> getAllPersons(
            String name,
            Occasion occasion,
            Relationship relationship,
            LocalDate eventDate,
            String keyword
    );

    PersonResponseDTO updatePerson(Long id,
                                   PersonRequestDTO request);

    void deletePerson(Long id);
}