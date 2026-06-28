package com.geranium.gift.service.impl;

import com.geranium.gift.exception.ResourceNotFoundException;
import com.geranium.gift.mapper.PersonMapper;
import com.geranium.gift.model.dto.PersonRequestDTO;
import com.geranium.gift.model.dto.PersonResponseDTO;
import com.geranium.gift.model.entity.Person;
import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.repository.PersonRepository;
import com.geranium.gift.repository.specification.PersonSpecification;
import com.geranium.gift.service.PersonService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper) {

        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonResponseDTO createPerson(
            PersonRequestDTO request) {

        Person person = personMapper.toEntity(request);

        Person savedPerson = personRepository.save(person);

        return personMapper.toResponseDTO(savedPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponseDTO getPersonById(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + id));

        return personMapper.toResponseDTO(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDTO> getAllPersons(
            String name,
            Occasion occasion,
            String keyword) {

        Specification<Person> spec = Specification
                .where(PersonSpecification.hasName(name))
                .and(PersonSpecification.hasOccasion(occasion))
                .and(PersonSpecification.hasKeyword(keyword));

        return personRepository.findAll(
                        spec,
                        Sort.by("name").ascending())
                .stream()
                .map(personMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PersonResponseDTO updatePerson(
            Long id,
            PersonRequestDTO request) {

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + id));

        person.setName(request.name());
        person.setOccasion(request.occasion());
        person.setNotes(request.notes());


        return personMapper.toResponseDTO(person);
    }

    @Override
    public void deletePerson(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + id));

        // CascadeType.ALL + orphanRemoval=true
        // automatically deletes all gifts
        personRepository.delete(person);
    }
}