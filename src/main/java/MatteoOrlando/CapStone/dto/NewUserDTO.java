package MatteoOrlando.CapStone.dto;

import MatteoOrlando.CapStone.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "Username is required!")
                         @Size(min = 3, max = 15, message = " your username must be  between 3 and 8 characters!")
                         String username,
                         @NotEmpty(message = "email is required!")
                         @Email(message = "please check your email format!")
                         String email,
                         @NotEmpty(message = "password is required!")
                         String password,
                         @NotEmpty(message = "name is required!")
                         @Size(min = 2, max = 10, message = " your name must be  between 3 and 10 characters!")
                         String name,
                         @NotEmpty(message = "surname is required!")
                         @Size(min = 2, max = 15, message = " your surname must be  between 3 and 15 characters!")
                         String surname,
                         UserType role)
{


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


}
