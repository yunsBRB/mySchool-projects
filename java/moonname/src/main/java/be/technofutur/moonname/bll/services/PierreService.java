package be.technofutur.moonname.bll.services;

import be.technofutur.moonname.dl.entities.Pierre;
import java.util.List;

public interface PierreService {
    List<Pierre> findAll();
    Pierre create(String inscription, String pays, Integer missionId, String username);
    Pierre deposer(Integer id);
}
