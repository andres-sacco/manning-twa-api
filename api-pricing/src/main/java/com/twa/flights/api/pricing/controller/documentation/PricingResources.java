package com.twa.flights.api.pricing.controller.documentation;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.twa.flights.api.pricing.dto.UpdatedPriceInfoDTO;
import com.twa.flights.common.dto.itinerary.ItineraryDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pricing", description = "Operations about the pricing")
public interface PricingResources {

    @Operation(description = "Get entity by iso code", responses = {
            @ApiResponse(responseCode = "200", description = "The itineraries with final price", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") }, tags = { "Pricing" })
    @PostMapping(value = "/itineraries")
    ResponseEntity<List<UpdatedPriceInfoDTO>> priceItineraries(@RequestBody List<ItineraryDTO> itineraries);

}
