package com.twa.flights.api.pricing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DocumentationControllerTest {

    private DocumentationController controller;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        response = mock(HttpServletResponse.class);
        controller = new DocumentationController();
    }

    @Test
    public void should_redirect_documentation_without_error() {
        controller.redirectToDocumentation(response);
    }

    @Test
    public void should_redirect_documentation_error() throws IOException {
        doThrow(new IOException()).when(response).sendRedirect(anyString());
        controller.redirectToDocumentation(response);
    }

    @Test
    public void should_redirect_documentation_error_message() throws IOException {
        doThrow(new IOException("as")).when(response).sendRedirect(anyString());
        controller.redirectToDocumentation(response);
    }

}
