package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.CartItem;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Long> {
     List<CartItem> findByUser(User user);

    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

}
