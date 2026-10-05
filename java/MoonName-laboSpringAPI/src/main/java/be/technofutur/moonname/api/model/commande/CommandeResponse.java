package be.technofutur.moonname.api.model.commande;

import be.technofutur.moonname.dl.entities.Commande;
import java.time.LocalDateTime;
import java.util.List;

public record CommandeResponse(Integer id, LocalDateTime dateCommande, List<String> inscriptions) {
    public static CommandeResponse from(Commande commande) {
        return new CommandeResponse(
                commande.getId(),
                commande.getDateCommande(),
                commande.getPierres().stream().map(p -> p.getInscription()).toList()
        );
    }
}
