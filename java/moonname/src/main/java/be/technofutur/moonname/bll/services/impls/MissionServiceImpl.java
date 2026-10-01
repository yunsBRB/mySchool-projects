package be.technofutur.moonname.bll.services.impls;

import be.technofutur.moonname.bll.services.MissionService;
import be.technofutur.moonname.dal.repositories.MissionRepository;
import be.technofutur.moonname.dl.entities.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;

    // Ici pas de magie : je lis simplement les missions en base.
    @Override public List<Mission> findAll() { return missionRepository.findAll(); }

    @Override public Mission findById(Integer id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mission introuvable"));
    }
}
