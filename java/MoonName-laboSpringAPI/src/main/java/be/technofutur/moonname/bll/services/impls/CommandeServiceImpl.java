package be.technofutur.moonname.bll.services.impls;

import be.technofutur.moonname.bll.services.*;
import be.technofutur.moonname.dal.repositories.*;
import be.technofutur.moonname.dl.entities.*;
import be.technofutur.moonname.dl.enums.StatutPierre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final PierreRepository pierreRepository;
    private final PanierService panierService;
    private final AuthService authService;

    // Je transforme juste le contenu du panier en commande.
    @Override @Transactional
    public Commande valider(String username) {
        Panier panier = panierService.getOrCreate(username);
        List<Pierre> pierres = pierreRepository.findByPanierIdOrderById(panier.getId());
        if (pierres.isEmpty()) throw new IllegalArgumentException("Panier vide");

        Commande commande = commandeRepository.save(new Commande(authService.findByUsername(username)));
        for (Pierre pierre : pierres) {
            pierre.setPanier(null);
            pierre.setCommande(commande);
            pierre.setStatut(StatutPierre.ACHETEE);
        }
        pierreRepository.saveAll(pierres);
        commande.setPierres(pierres);
        return commande;
    }

    @Override public List<Commande> findMine(String username) {
        return commandeRepository.findByClientUsernameOrderByIdDesc(username);
    }
}
