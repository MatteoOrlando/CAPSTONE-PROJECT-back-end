package MatteoOrlando.CapStone.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginResponseDTO(@NotEmpty(message = "Token is required!")
                                   String accessToken) {
}
