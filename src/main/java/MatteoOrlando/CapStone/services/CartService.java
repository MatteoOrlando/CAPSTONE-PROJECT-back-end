package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.CartItemDTO;
import MatteoOrlando.CapStone.entities.CartItem;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.repositories.CartItemDAO;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemDAO cartItemRepository;

    @Autowired
    private ProductDAO productRepository;

    public CartItem addToCart(User user, CartItemDTO cartItemDTO) {
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public void removeFromCart(User user, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }
}
