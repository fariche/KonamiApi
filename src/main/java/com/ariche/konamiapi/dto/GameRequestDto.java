package com.ariche.konamiapi.dto;

import com.ariche.konamiapi.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto for the request of the submitter. Both fields are validated.
 */
@Data
@AllArgsConstructor
public class GameRequestDto {

    @NotBlank(message = "Game name must not be blank")
    @Size(max = 100, message = "Game name must not exceed 100 chars")
    private String name;

    @NotNull(message = "Game Type cannot be null")
    private GameType type;
}
