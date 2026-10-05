package be.technofutur.moonname.bll.services.impls;

import be.technofutur.moonname.bll.services.*;
import be.technofutur.moonname.dal.repositories.PierreRepository;
import be.technofutur.moonname.dl.entities.*;
import be.technofutur.moonname.dl.enums.StatutPierre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PierreServiceImpl implements PierreService {
    private final PierreRepository pierreRepository;
    private final MissionService missionService;
    private final AuthService authService;

    @Override
    public List<Pierre> findAll() { return pierreRepository.findAll(); }

    // Le client choisit une mission et je lie sa pierre a son compte
    @Override

    public Pierre create(String inscription, String pays, Integer missionId, String username) {
        Mission mission = missionService.findById(missionId);
        User client = authService.findByUsername(username);
        return pierreRepository.save(new Pierre(inscription, pays, mission, client));
    }

    // L'astronaute confirme seulement le depot
    @Override
    public Pierre deposer(Integer id) {
        Pierre pierre = pierreRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pierre introuvable"));

        if (pierre.getStatut() != StatutPierre.ACHETEE) {
            throw new IllegalArgumentException(
                    "La pierre doit être achetée avant son depot");
        }

        pierre.setStatut(StatutPierre.DEPOSEE);
        return pierreRepository.save(pierre);
    }
    }

