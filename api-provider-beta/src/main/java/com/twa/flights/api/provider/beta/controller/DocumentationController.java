package com.twa.flights.api.provider.beta.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@Controller
@RequestMapping("documentation")
public class DocumentationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentationController.class);

    @ResponseBody
    @GetMapping
    public void redirectToDocumentation(HttpServletResponse response) {
        try {
            response.sendRedirect("swagger-ui.html");
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR: ");
            if (e.getMessage() != null) {
                sb.append(e.getMessage());
            }
            LOGGER.error(sb.toString(), e);
        }
    }
}