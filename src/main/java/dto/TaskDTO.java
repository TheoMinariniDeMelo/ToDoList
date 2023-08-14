package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        @NotEmpty String taskDescription,
        @NotNull String title,
        @NotNull UUID userId,
        @NotBlank LocalDateTime dataCreate,
        @NotBlank LocalDateTime dataUpdate
) {
}
