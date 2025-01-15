package com.ariche.konamiapi.service;

import com.ariche.konamiapi.dto.GameRequestDto;
import com.ariche.konamiapi.dto.GameResponseDto;
import com.ariche.konamiapi.enums.GameType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Simulates saving a game into the database and handles multithreading.
 */
@Service
@Slf4j
public class GameService {

    /**
     * Async used to handle multiple client submissions. Simulates saving data into the database.
     * Currently just logs the game information, then returns a response object.
     * @param gameRequestDto
     * @return
     */
    @Async
    public CompletableFuture<GameResponseDto> save(GameRequestDto gameRequestDto) {
        //Creates a random gameUid to save
        String gameUid = UUID.randomUUID().toString();

        //Creates local time
        LocalDateTime currentTime = LocalDateTime.now();

        //Logs game details
        log.info("Game Saved: {}, Game Type: {}, Game ID: {}, Time: {}",
                gameRequestDto.getName(), gameRequestDto.getType(), gameUid, currentTime);

        //Return completeabelFuture Response DTO for multithreading
        return CompletableFuture.completedFuture(new GameResponseDto(
                gameUid,
                gameRequestDto.getName(),
                gameRequestDto.getType(),
                currentTime
        ));
    }
}
