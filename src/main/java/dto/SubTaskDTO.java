package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubTaskDTO(
        @NotBlank String subTaskDescription,
        @NotNull String title,
        @NotEmpty UUID task,
        @NotBlank LocalDateTime dataCreate,
        @NotBlank LocalDateTime dataUpdate
) {
}
