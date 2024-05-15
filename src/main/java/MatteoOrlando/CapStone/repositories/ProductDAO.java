package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
    
   // List<Product> findByPlatforms_Id(Long platformId);

    }

