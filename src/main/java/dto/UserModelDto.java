package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserModelDto(
        @NotNull String user,
        @NotBlank String password,
        @NotBlank String email,
        @NotBlank LocalDateTime dataCreate,
        @NotBlank LocalDateTime dataUpdate
) {}
