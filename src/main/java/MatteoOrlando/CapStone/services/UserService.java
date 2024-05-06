package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.NewUserDTO;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.UserDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserDAO ud;

    @Autowired
    private PasswordEncoder bcrypt;


    public Page<User> getUsers(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.ud.findAll(pageable);
    }

    public User save(NewUserDTO body) {
        this.ud.findByEmail(body.email())
                .ifPresent(user -> {
                    try {
                        throw new BadRequestException(" email " + user.getEmail() + " already in use!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                });

        this.ud.findByUsername(body.username())
                .ifPresent(user -> {
                    try {
                        throw new BadRequestException(" username " + user.getUsername() + " already  in use!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                });
        User newUser = new User(body.username(), body.email(), bcrypt.encode(body.password()), body.name(), body.surname());
        return this.ud.save(newUser);
    }

    public User findById(long id) {
        return this.ud.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findUserById(long id) {
        return this.ud.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIDAndUpdate(long id, NewUserDTO body) {
        User found = this.findById(id);

        this.ud.findByEmail(body.email())
                .ifPresent(user -> {
                    throw new BadRequestException(" email " + user.getEmail() + " already in use!");
                });

        this.ud.findByUsername(body.username())
                .ifPresent(user -> {
                    throw new BadRequestException(" username " + user.getUsername() + " already  in use!");
                });

        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(bcrypt.encode(body.password()));
        found.setName(body.name());
        found.setSurname(body.surname());
        if (!found.getAvatar().contains("cloudinary")) found.setTemporaryAvatar();
        this.ud.save(found);
        return found;
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        this.ud.delete(found);
    }

    public User findByEmail(String email) {
        return ud.findByEmail(email).orElseThrow(() -> new NotFoundException("User with " + email + " not found!"));
    }

}
