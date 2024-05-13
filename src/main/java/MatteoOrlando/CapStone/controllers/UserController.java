package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.NewUserDTO;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.enums.UserType;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.us.getUsers(page, size, sortBy);
    }

    @GetMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserType getUserRole(@PathVariable long userId) {
        User user = us.findById(userId);
        return user.getRole();
    }

    @PostMapping("/normal")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNormalUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.createUser(body, false);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUserAsAdmin(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.createUserAsAdmin(body, true);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User findUserById(@PathVariable long userId) {
        return us.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User findUserByIdAndUpdate(@PathVariable long userId, @RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.findByIDAndUpdate(userId, body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentUser) {
        this.us.findByIdAndDelete(currentUser.getId());
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findUserByIdAndDelete(@PathVariable long userId) {
        us.findByIdAndDelete(userId);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #email == principal.username")
    public User getUserByEmail(@PathVariable String email) {
        User user = us.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("User not found with email: " + email);
        }
        return user;
    }
}

