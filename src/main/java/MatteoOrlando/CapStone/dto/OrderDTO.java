package MatteoOrlando.CapStone.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderDTO(
        Long id,
        @NotNull(message = "User ID is required")
        Long userId,
        @NotEmpty(message = "Product IDs are required")
        List<Long> productIds,
        @Positive(message = "Total price must be positive")
        Double totalPrice,
        @NotEmpty(message = "Status is required")
        String status
) {
}
