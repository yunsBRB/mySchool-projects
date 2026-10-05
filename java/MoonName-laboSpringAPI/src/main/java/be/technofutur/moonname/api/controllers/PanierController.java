package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.panier.PanierResponse;
import be.technofutur.moonname.api.model.pierre.PierreResponse;
import be.technofutur.moonname.api.model.user.UserContext;
import be.technofutur.moonname.bll.services.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
public class PanierController {
    private final PanierService panierService;

    @GetMapping
    public PanierResponse contenu(@AuthenticationPrincipal UserContext user) {
        return PanierResponse.from(panierService.contenu(user.username()));
    }

    // Une pierre est unique donc pas besoin de quantite comme un produit classique
    @PostMapping("/{pierreId}")
    public PierreResponse ajouter(@PathVariable Integer pierreId,
                                  @AuthenticationPrincipal UserContext user) {
        return PierreResponse.from(panierService.ajouter(pierreId, user.username()));
    }
}
