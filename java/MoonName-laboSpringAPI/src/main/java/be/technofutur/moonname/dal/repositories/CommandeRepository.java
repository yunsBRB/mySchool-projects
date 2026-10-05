package be.technofutur.moonname.dal.repositories;

import be.technofutur.moonname.dl.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByClientUsernameOrderByIdDesc(String username);
}
