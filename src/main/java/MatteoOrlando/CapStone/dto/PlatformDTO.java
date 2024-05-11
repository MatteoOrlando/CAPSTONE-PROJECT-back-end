package MatteoOrlando.CapStone.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PlatformDTO(
        Long id,
        @NotEmpty(message = "Platform name is required")
        @Size(min = 2, max = 100, message = "Platform name must be between 2 and 100 characters")
        String name
) {
}
