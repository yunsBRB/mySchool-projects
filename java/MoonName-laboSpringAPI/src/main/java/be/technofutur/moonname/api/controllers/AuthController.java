package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.user.*;
import be.technofutur.moonname.api.utils.JwtUtils;
import be.technofutur.moonname.bll.services.AuthService;
import be.technofutur.moonname.dal.repositories.UserRepository;
import be.technofutur.moonname.dl.entities.User;
import be.technofutur.moonname.dl.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(tokens(authService.login(request.username(), request.password())));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        String username = request.username().trim();

        if (userRepository.existsByUsername(username))
            throw new IllegalArgumentException("Ce nom d’utilisateur est déjà utilisé");

        if (request.password().getBytes(StandardCharsets.UTF_8).length > 72)
            throw new IllegalArgumentException("Le mot de passe est trop long");

        User user = userRepository.save(new User(
                username, passwordEncoder.encode(request.password()), Role.CLIENT
        ));

        return ResponseEntity.ok(tokens(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        String username = jwtUtils.getRefreshUsername(request.refreshToken());
        User user = authService.findByUsername(username);

        return ResponseEntity.ok(new TokenResponse(
                user.getUsername(), user.getRole().name(),
                jwtUtils.generateAccess(user), request.refreshToken()
        ));
    }

    private TokenResponse tokens(User user) {
        return new TokenResponse(
                user.getUsername(), user.getRole().name(),
                jwtUtils.generateAccess(user), jwtUtils.generateRefresh(user)
        );
    }

    public record RegisterRequest(
            @NotBlank @Size(max = 50) String username,
            @NotBlank @Size(min = 8, max = 72) String password
    ) { }
}