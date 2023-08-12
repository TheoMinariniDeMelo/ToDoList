package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListSecondaryDto(@NotBlank String describe_task, @NotNull String title, @NotNull UUID task_id,
                               @NotBlank LocalDateTime dataCreate, @NotBlank LocalDateTime dataUpdate) {
}
