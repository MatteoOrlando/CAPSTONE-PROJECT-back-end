package MatteoOrlando.CapStone.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewDTO(
        Long id,
        @NotNull(message = "User ID is required")
        Long userId,
        @NotNull(message = "Product ID is required")
        Long productId,
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be no more than 5")
        int rating,
        @NotEmpty(message = "Comment is required")
        @Size(max = 500, message = "Comment can be up to 500 characters long")
        String comment
) {
}
