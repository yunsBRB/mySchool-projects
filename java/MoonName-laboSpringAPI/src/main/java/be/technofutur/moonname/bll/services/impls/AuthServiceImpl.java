package be.technofutur.moonname.bll.services.impls;

import be.technofutur.moonname.bll.services.AuthService;
import be.technofutur.moonname.dal.repositories.UserRepository;
import be.technofutur.moonname.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Je verifie juste que le compte existe et que le mot de passe colle.
    @Override public User login(String username, String password) {
        User user = findByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Mauvais mot de passe");
        }
        return user;
    }

    @Override public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
    }
}
