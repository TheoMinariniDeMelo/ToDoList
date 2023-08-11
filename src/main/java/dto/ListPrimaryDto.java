package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListPrimaryDto(@NotBlank String describe_task, @NotNull String title) {

}
