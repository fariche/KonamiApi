package com.ariche.konamiapi.controller;

import com.ariche.konamiapi.BaseTest;
import com.ariche.konamiapi.dto.GameRequestDto;
import com.ariche.konamiapi.dto.GameResponseDto;
import com.ariche.konamiapi.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@WebFluxTest(GameController.class)
public class GameControllerTest extends BaseTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenSaveGame_thenResponseCreated() {

        //Create Dtos for test
        GameResponseDto gameResponseDto = buildGameResponseDto();
        GameRequestDto gameRequestDto = buildGameRequestDto();

        //Mocks
        Mockito.when(gameService.save(any(GameRequestDto.class))).thenReturn(CompletableFuture.completedFuture(gameResponseDto));

        //Test for a successful game creation
        webTestClient.post()
                .uri(GAME_API_URL)
                .bodyValue(gameRequestDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(GameResponseDto.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals(gameRequestDto.getName(), gameResponseDto.getName());
                    assertEquals(gameRequestDto.getType(), gameResponseDto.getType());
                });
    }
}
