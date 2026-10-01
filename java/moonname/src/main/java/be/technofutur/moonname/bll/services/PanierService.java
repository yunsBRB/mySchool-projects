package be.technofutur.moonname.bll.services;

import be.technofutur.moonname.dl.entities.Panier;
import be.technofutur.moonname.dl.entities.Pierre;
import java.util.List;

public interface PanierService {
    Panier getOrCreate(String username);
    Pierre ajouter(Integer pierreId, String username);
    List<Pierre> contenu(String username);
}
