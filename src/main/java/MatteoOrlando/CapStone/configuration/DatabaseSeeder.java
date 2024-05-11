/* package MatteoOrlando.CapStone.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.enums.UserType;
import MatteoOrlando.CapStone.repositories.UserDAO;

@Component
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserDAO userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {

                User admin = new User("FINAL ADMIN", "TestAdmin@email.com", passwordEncoder.encode("TESTADMIN"), "ADMIN", "FADMIN", UserType.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin user created");
            }
        };
    }
}*/
