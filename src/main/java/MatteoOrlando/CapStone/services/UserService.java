package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.repositories.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public List<User> findAllUser() {
        return userDAO.findAll;
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente con ID:" + id + "non trovato!"));
    }

    @Transactional(readOnly = true)
    public User findUserByEmail( String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utente con Email:" + email + "non trovato!"));
    }

    @Transactional
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

}
