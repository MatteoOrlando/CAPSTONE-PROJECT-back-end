package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformDAO extends JpaRepository<Platform, Long> {

    Platform findByName(String name);
}
