package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserModelDto(@NotNull String user, @NotBlank String password, @NotBlank String email) {
}
