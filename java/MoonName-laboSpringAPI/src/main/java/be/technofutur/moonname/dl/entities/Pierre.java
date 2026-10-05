package be.technofutur.moonname.dl.entities;

import be.technofutur.moonname.dl.enums.StatutPierre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pierre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String inscription;

    @Column(nullable = false, length = 40)
    private String pays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPierre statut = StatutPierre.EN_ATTENTE;

    @ManyToOne(optional = false)
    private Mission mission;

    @ManyToOne(optional = false)
    private User client;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Commande commande;

    public Pierre(String inscription, String pays, Mission mission, User client) {
        this.inscription = inscription;
        this.pays = pays;
        this.mission = mission;
        this.client = client;
    }
}
