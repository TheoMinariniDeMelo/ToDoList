package dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationDto(@NotEmpty String email,
                                @NotEmpty String password
) {
}
