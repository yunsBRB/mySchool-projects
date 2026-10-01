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
public class PanierServiceImpl implements PanierService {
    private final PanierRepository panierRepository;
    private final PierreRepository pierreRepository;
    private final AuthService authService;

    // Comme dans l'exo du prof : si le panier existe pas, je le cree.
    @Override public Panier getOrCreate(String username) {
        return panierRepository.findByClientUsername(username)
                .orElseGet(() -> panierRepository.save(new Panier(authService.findByUsername(username))));
    }

    @Override @Transactional
    public Pierre ajouter(Integer pierreId, String username) {
        Pierre pierre = pierreRepository.findById(pierreId)
                .orElseThrow(() -> new IllegalArgumentException("Pierre introuvable"));

        if (!pierre.getClient().getUsername().equals(username))
            throw new IllegalArgumentException("Cette pierre est pas a toi");
        if (pierre.getStatut() != StatutPierre.EN_ATTENTE)
            throw new IllegalArgumentException("Pierre deja utilisee");

        pierre.setPanier(getOrCreate(username));
        pierre.setStatut(StatutPierre.PANIER);
        return pierreRepository.save(pierre);
    }

    @Override public List<Pierre> contenu(String username) {
        return pierreRepository.findByPanierIdOrderById(getOrCreate(username).getId());
    }
}
