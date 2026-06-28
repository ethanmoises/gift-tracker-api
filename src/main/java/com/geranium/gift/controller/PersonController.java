package com.geranium.gift.controller;

import com.geranium.gift.model.dto.PersonRequestDTO;
import com.geranium.gift.model.dto.PersonResponseDTO;
import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Create a new person")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public PersonResponseDTO createPerson(
            @RequestBody PersonRequestDTO request) {

        return personService.createPerson(request);
    }

    @Operation(summary = "Retrieve a person by ID")
    @GetMapping("/{id}")
    public PersonResponseDTO getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @Operation(summary = "Retrieve all persons with optional filters")
    @GetMapping
    public List<PersonResponseDTO> getAllPersons(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Occasion occasion,
            @RequestParam(required = false) String keyword) {

        return personService.getAllPersons(name, occasion, keyword);
    }

    @Operation(summary = "Update an existing person")
    @PutMapping("/{id}")
    public PersonResponseDTO updatePerson(
            @PathVariable Long id,
            @RequestBody PersonRequestDTO request) {

        return personService.updatePerson(id, request);
    }

    @Operation(summary = "Delete a person by ID")
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}