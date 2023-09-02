package application.dto.subTask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateDto(
        @NotNull String title,
        @NotNull String description,
        @NotBlank UUID task
) {
}