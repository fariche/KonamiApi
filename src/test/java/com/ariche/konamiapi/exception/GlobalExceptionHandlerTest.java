package com.ariche.konamiapi.exception;

import com.ariche.konamiapi.BaseTest;
import com.ariche.konamiapi.controller.GameController;
import com.ariche.konamiapi.dto.ErrorResponseDto;
import com.ariche.konamiapi.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = GameController.class)
public class GlobalExceptionHandlerTest extends BaseTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GameService gameService;

    @Test
    public void whenInvalidRequest_thenReturn500() {

        //Test exception handling of 500 error
        webTestClient.post()
                .uri(GAME_API_URL)
                .bodyValue(buildInvalidGameRequestDto())
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(ErrorResponseDto.class)
                .value(errorResponse -> {
                    assertNotNull(errorResponse.getMessage());
                    assertEquals(errorResponse.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR.value());
                });

    }
}
