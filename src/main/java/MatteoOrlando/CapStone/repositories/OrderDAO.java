package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderDAO extends JpaRepository<Order, Long> {

    boolean existsById(Long id);
}
