/*package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.NewUserDTO;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private UserService us;

    @PostMapping("/create-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createInitialAdmin(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.createUserAsAdmin(body, true);
    }
}
*/