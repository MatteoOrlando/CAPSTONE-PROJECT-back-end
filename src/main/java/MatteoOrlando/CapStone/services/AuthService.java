package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.UserLoginDTO;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.exceptions.UnauthorizedException;
import MatteoOrlando.CapStone.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService us;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jt;

    public String authenticateUsersAndGenerateToken(UserLoginDTO body) {
        User user = this.us.findByEmail(body.email());
        if (bcrypt.matches(body.password(), (user).getPassword())) {
            return jt.createToken(user);

        } else {
            throw new UnauthorizedException("Credenziali non valide, riprova il log in!");
        }
    }
    public String login(UserLoginDTO payload) {
        User found = this.us.findByEmail(payload.email());
        if (found == null){
            throw new UnauthorizedException("User " + payload.email() + " has not been found");
        }else if (!bcrypt.matches(payload.password(), found.getPassword()))
            throw new UnauthorizedException("Password is wrong");
        return jt.createToken(found);
    }
}
