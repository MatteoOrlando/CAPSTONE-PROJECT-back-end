package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryDAO extends JpaRepository<Category, Long> {

}
