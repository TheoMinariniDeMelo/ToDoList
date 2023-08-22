package application.dto.user;

import jakarta.validation.constraints.NotNull;

public record UpdateDto(@NotNull String user, @NotNull String password, @NotNull String email, String emailNew ,  @NotNull String passwordNew) {

}
