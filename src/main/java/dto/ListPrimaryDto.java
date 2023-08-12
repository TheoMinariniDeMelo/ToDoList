package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListPrimaryDto(@NotBlank String describe_task, @NotNull String title, @NotNull UUID user_id,
                             @NotBlank LocalDateTime dataCreate, @NotBlank LocalDateTime dataUpdate) {

}
