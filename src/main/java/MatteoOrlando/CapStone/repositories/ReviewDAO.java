package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
