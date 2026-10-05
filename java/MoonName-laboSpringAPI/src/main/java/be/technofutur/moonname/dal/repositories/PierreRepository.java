package be.technofutur.moonname.dal.repositories;

import be.technofutur.moonname.dl.entities.Pierre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PierreRepository extends JpaRepository<Pierre, Integer> {
    List<Pierre> findByPanierIdOrderById(Integer panierId);
}
