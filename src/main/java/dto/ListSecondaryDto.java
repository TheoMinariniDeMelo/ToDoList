package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListSecondaryDto(@NotBlank String describe_task,@NotNull String title) {
}
