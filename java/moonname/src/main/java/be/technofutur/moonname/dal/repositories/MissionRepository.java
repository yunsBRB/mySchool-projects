package be.technofutur.moonname.dal.repositories;

import be.technofutur.moonname.dl.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> { }
