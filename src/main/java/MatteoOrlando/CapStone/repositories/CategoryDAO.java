package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CategoryDAO extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
