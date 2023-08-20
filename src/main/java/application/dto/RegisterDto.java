package application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(
        @NotNull String email,
        @NotNull String user,
        @NotNull String password
) {
}
