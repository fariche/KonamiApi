package com.ariche.konamiapi.dto;


import com.ariche.konamiapi.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Dto for a response with no errors.
 */
@Data
@AllArgsConstructor
public class GameResponseDto {
    private String gameId;
    private String name;
    private GameType type;
    private LocalDateTime timestamp;
}
