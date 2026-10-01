package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.user.*;
import be.technofutur.moonname.api.utils.JwtUtils;
import be.technofutur.moonname.bll.services.AuthService;
import be.technofutur.moonname.dl.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    // Login normal : je renvoie un token court + un refresh plus long
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(tokens(authService.login(request.username(), request.password())));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        String username = jwtUtils.getRefreshUsername(request.refreshToken());
        User user = authService.findByUsername(username);
        return ResponseEntity.ok(new TokenResponse(
                user.getUsername(), user.getRole().name(), jwtUtils.generateAccess(user), request.refreshToken()
        ));
    }

    private TokenResponse tokens(User user) {
        return new TokenResponse(
                user.getUsername(), user.getRole().name(),
                jwtUtils.generateAccess(user), jwtUtils.generateRefresh(user)
        );
    }
}
