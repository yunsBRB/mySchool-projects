package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.commande.CommandeResponse;
import be.technofutur.moonname.api.model.user.UserContext;
import be.technofutur.moonname.bll.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
public class CommandeController {
    private final CommandeService commandeService;

    @PostMapping
    public CommandeResponse valider(@AuthenticationPrincipal UserContext user) {
        return CommandeResponse.from(commandeService.valider(user.username()));
    }

    @GetMapping
    public List<CommandeResponse> mesCommandes(@AuthenticationPrincipal UserContext user) {
        return commandeService.findMine(user.username()).stream().map(CommandeResponse::from).toList();
    }
}
