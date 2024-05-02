package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Product;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product, Long> {

    /* @Override
    @NonNull
    List<Product> findAll();*/

    @Override
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @Override
    @NonNull
    <S extends Product> S save(@NonNull S product);

    @Override
    void deleteById(@NonNull Long id);

    List<Product> findByName(String name);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPlatformId(Long platformId);
}
