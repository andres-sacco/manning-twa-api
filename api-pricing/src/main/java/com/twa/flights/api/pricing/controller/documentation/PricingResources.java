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
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pricing", description = "Operations about the pricing")
public interface PricingResources {

    @Operation(description = "Get entity by iso code", responses = {
            @ApiResponse(responseCode = "200", description = "The itineraries with final price", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(name = "Itineraries", summary = "Example of list of itineraries", value = "[{\"id\":\"b131c0a4-0476-4cb2-88bc-54466796ad0e\",\"segments\":[{\"legs\":[{\"origin\":\"BUE\",\"destination\":\"MIA\",\"departureDate\":\"2022-03-29\",\"departureTime\":\"06:00\",\"arrivalDate\":\"2022-03-29\",\"arrivalTime\":\"12:00\",\"number\":2,\"flightDuration\":\"07:00\"}],\"flightDuration\":\"07:00\"},{\"legs\":[{\"origin\":\"MIA\",\"destination\":\"BUE\",\"departureDate\":\"2022-04-03\",\"departureTime\":\"12:00\",\"arrivalDate\":\"2022-04-03\",\"arrivalTime\":\"18:00\",\"number\":5,\"flightDuration\":\"05:00\"}],\"flightDuration\":\"05:00\"}],\"priceInfo\":{\"adults\":{\"tax\":194.92,\"base\":287.49,\"quantity\":1,\"subtotal\":482.41,\"total\":482.41,\"type\":\"ADULT\",\"markup\":null},\"children\":null,\"infants\":null},\"provider\":\"BETA\",\"flightType\":\"ROUND_TRIP\",\"carrier\":\"AR\"}]"))), tags = {
                    "Pricing" })
    @PostMapping(value = "/itineraries")
    ResponseEntity<List<UpdatedPriceInfoDTO>> priceItineraries(@RequestBody List<ItineraryDTO> itineraries);

}
