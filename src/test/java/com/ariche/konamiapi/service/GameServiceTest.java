package com.ariche.konamiapi.service;

import com.ariche.konamiapi.BaseTest;
import com.ariche.konamiapi.dto.GameRequestDto;
import com.ariche.konamiapi.dto.GameResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class GameServiceTest extends BaseTest {

    @InjectMocks
    private GameService gameService;

    @Test
    public void whenSaveGame_thenReturnValidResponse() throws ExecutionException, InterruptedException {

        //Build the request to be sent
        GameRequestDto gameRequestDto = buildGameRequestDto();

        //Return the response as a CompleteableFuture
        CompletableFuture<GameResponseDto> asyncGameResponseDto = gameService.save(gameRequestDto);

        //Verify the response has data
        assertNotNull(asyncGameResponseDto);

        //verify the async portion is complete
        assertTrue(asyncGameResponseDto.isDone());

        //verify data in the object
        GameResponseDto gameResponseDto = asyncGameResponseDto.get();
        assertEquals(gameRequestDto.getName(), gameResponseDto.getName());
        assertEquals(gameRequestDto.getType(), gameResponseDto.getType());
        assertNotNull(gameResponseDto.getGameId());
        assertNotNull(gameResponseDto.getTimestamp());

    }

    @Test
    public void when10Threads_thenHanldeAllRequests() throws InterruptedException {

        //Sync threads
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        //Create threadpool
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


        //Tests concurrent threads
        IntStream.range(0, NUMBER_OF_THREADS).forEach(threadNumber -> {

            GameRequestDto gameRequestDto = buildGameRequestDto();
            gameRequestDto.setName("Game Thread " + threadNumber);

            executorService.submit(() -> {
                try {
                    gameService.save(gameRequestDto).get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        });

        //Wait 5 seconds for all threads to complete
        boolean tasksComplete = latch.await(5, TimeUnit.SECONDS);
        assertTrue(tasksComplete, TASK_COMPETE_MESSAGE);
    }

}
