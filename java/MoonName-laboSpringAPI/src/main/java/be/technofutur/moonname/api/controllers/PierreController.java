package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.pierre.*;
import be.technofutur.moonname.api.model.user.UserContext;
import be.technofutur.moonname.bll.services.PierreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pierres")
@RequiredArgsConstructor
public class PierreController {
    private final PierreService pierreService;

    // Pour la demo je garde la liste publique
    @GetMapping
    public List<PierreResponse> findAll() {
        return pierreService.findAll().stream().map(PierreResponse::from).toList();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
    public PierreResponse create(@Valid @RequestBody PierreRequest request,
                                 @AuthenticationPrincipal UserContext user) {
        return PierreResponse.from(pierreService.create(request.inscription(), request.pays(), request.missionId(), user.username()));
    }

    @PatchMapping("/{id}/deposer")
    @PreAuthorize("hasAnyAuthority('ASTRONAUTE','ADMIN')")
    public PierreResponse deposer(@PathVariable Integer id) {
        return PierreResponse.from(pierreService.deposer(id));
    }
}
