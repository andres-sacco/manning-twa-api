package com.twa.flights.api.catalog.controller.documentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.twa.flights.api.catalog.dto.BaseDTO;
import com.twa.flights.api.catalog.dto.CityDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Catalog", description = "Operations about the catalog")
public interface CatalogResources {

    @Operation(description = "Get entity by iso code", responses = {
            @ApiResponse(responseCode = "200", description = "The city information", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") }, tags = {
                    "Catalog" }, parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "code", description = "The code of the city (e.g. BUE, MIA, SCL, NYC, PAR, LON)", required = true, example = "BUE") })
    @GetMapping(value = "/city/{code}")
    ResponseEntity<CityDTO> getCityByCode(@PathVariable String code);

}
