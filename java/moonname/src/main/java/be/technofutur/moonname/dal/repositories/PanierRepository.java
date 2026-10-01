package be.technofutur.moonname.dal.repositories;

import be.technofutur.moonname.dl.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    Optional<Panier> findByClientUsername(String username);
}
