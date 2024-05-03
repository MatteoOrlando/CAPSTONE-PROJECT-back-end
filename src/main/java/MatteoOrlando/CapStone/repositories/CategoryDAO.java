package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
