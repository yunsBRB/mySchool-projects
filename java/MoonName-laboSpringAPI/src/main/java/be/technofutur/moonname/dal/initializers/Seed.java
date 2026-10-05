package be.technofutur.moonname.dal.initializers;

import be.technofutur.moonname.dal.repositories.*;
import be.technofutur.moonname.dl.entities.*;
import be.technofutur.moonname.dl.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override public void run(String... args) {
        addUser("michael", "moonwalk", Role.CLIENT);
        addUser("elvis", "vegas", Role.ASTRONAUTE);
        addUser("admin", "admin", Role.ADMIN);
    }

    private void addUser(String username, String password, Role role) {
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(new User(username, passwordEncoder.encode(password), role));
        }
    }
}
