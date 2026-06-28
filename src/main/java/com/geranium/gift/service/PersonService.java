package com.geranium.gift.service;

import com.geranium.gift.model.dto.PersonRequestDTO;
import com.geranium.gift.model.dto.PersonResponseDTO;
import com.geranium.gift.model.enums.Occasion;

import java.util.List;

public interface PersonService {

    PersonResponseDTO createPerson(PersonRequestDTO request);

    PersonResponseDTO getPersonById(Long id);

    List<PersonResponseDTO> getAllPersons(
            String name,
            Occasion occasion,
            String keyword
    );

    PersonResponseDTO updatePerson(Long id,
                                   PersonRequestDTO request);

    void deletePerson(Long id);
}