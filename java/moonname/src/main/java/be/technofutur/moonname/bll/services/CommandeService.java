package be.technofutur.moonname.bll.services;

import be.technofutur.moonname.dl.entities.Commande;
import java.util.List;

public interface CommandeService {
    Commande valider(String username);
    List<Commande> findMine(String username);
}
