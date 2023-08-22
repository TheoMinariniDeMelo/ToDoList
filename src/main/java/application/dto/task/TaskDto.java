package application.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskDto(
        @NotNull String title,
        @NotNull String describe
) {
}
