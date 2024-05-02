package MatteoOrlando.CapStone.repositories;

import MatteoOrlando.CapStone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    //cerca utente per nome
    List<User> findByName(String name);

    //cerca utente per email
    Optional<User> findByEmail(String email);
}
