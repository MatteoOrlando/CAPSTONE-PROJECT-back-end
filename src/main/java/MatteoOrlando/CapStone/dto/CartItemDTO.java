package MatteoOrlando.CapStone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;


}
