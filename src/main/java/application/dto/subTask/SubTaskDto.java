package application.dto.subTask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubTaskDto(
        @NotNull String title,
        @NotNull String subTitle,
        @NotBlank UUID task
) {
}
