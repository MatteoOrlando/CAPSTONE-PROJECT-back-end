package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.NewUserDTO;
import MatteoOrlando.CapStone.dto.UserLoginDTO;
import MatteoOrlando.CapStone.dto.UserLoginResponseDTO;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.AuthService;
import MatteoOrlando.CapStone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService us;

    @Autowired
    private AuthService as;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.as.authenticateUsersAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.save(body);
    }
}
