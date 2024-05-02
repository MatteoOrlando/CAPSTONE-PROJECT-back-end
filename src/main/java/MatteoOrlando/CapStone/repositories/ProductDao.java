package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    // Trova prodotti che corrispondono ad una parte del nome
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId (Long categoryId);
    List<Product> findByPlatformId (Long platformId);

}
