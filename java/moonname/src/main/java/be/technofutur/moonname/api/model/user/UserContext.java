package be.technofutur.moonname.api.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

public record UserContext(Integer id, String username, String role) {
    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
