package com.twa.flights.api.catalog.controller.documentation;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.twa.flights.api.catalog.dto.ErrorDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Errors", description = "Errors that can appears in this microservice")
public interface ErrorsResources {

    @Operation(description = "Errors related with this microservice", responses = {
            @ApiResponse(responseCode = "200", description = "List of errors that can appears during the execution of any endpoint", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping
    ResponseEntity<List<ErrorDTO>> getErrors();
}
