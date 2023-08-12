package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubTaskDTO(
        @NotBlank String describe_task,
        @NotNull String title,
        @NotBlank UUID taskId,
        @NotBlank LocalDateTime dataCreate,
        @NotBlank LocalDateTime dataUpdate
) {}
