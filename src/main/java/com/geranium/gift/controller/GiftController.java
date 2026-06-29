package com.geranium.gift.controller;

import com.geranium.gift.model.dto.GiftRequestDTO;
import com.geranium.gift.model.dto.GiftResponseDTO;
import com.geranium.gift.model.enums.GiftStatus;
import com.geranium.gift.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @Operation(summary = "Add a new gift for a specific person")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Gift added successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Person not found"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request")
    })
    @PostMapping("/person/{personId}")
    public GiftResponseDTO addGift(
            @PathVariable Long personId,
            @RequestBody GiftRequestDTO request) {

        return giftService.addGift(personId, request);
    }

    @Operation(summary = "Retrieve a gift by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Gift found"),
            @ApiResponse(responseCode = "404",
                    description = "Gift not found")
    })
    @GetMapping("/{id}")
    public GiftResponseDTO getGiftById(
            @PathVariable Long id) {

        return giftService.getGiftById(id);
    }

    @Operation(summary = "Retrieve all gifts with optional filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "List of gifts returned successfully")
    })
    @GetMapping
    public List<GiftResponseDTO> getAllGifts(

            @RequestParam(required = false)
            String idea,

            @RequestParam(required = false)
            GiftStatus status,

            @RequestParam(required = false)
            Double minPrice,

            @RequestParam(required = false)
            Double maxPrice,

            @RequestParam(required = false)
            String personName) {

        return giftService.getAllGifts(
                idea,
                status,
                minPrice,
                maxPrice,
                personName
        );
    }

    @Operation(summary = "Update an existing gift")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Gift updated successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Gift not found"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid request")
    })
    @PutMapping("/{id}")
    public GiftResponseDTO updateGift(
            @PathVariable Long id,
            @RequestBody GiftRequestDTO request) {

        return giftService.updateGift(id, request);
    }

    @Operation(summary = "Delete a gift by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Gift deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Gift not found")
    })
    @DeleteMapping("/{id}")
    public void deleteGift(
            @PathVariable Long id) {

        giftService.deleteGift(id);
    }
}