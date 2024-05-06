package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformDAO extends JpaRepository<Platform, Long> {

    Optional<Platform> findByName(String name);
}
