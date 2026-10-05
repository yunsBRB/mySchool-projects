package be.technofutur.moonname.bll.services;

import be.technofutur.moonname.dl.entities.Mission;
import java.util.List;

public interface MissionService {
    List<Mission> findAll();
    Mission findById(Integer id);
}
