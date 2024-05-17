package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.CartItem;
import MatteoOrlando.CapStone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}
