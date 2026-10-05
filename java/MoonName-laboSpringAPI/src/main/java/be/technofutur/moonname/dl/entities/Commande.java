package be.technofutur.moonname.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User client;

    @Column(nullable = false)
    private LocalDateTime dateCommande;

    @OneToMany(mappedBy = "commande", fetch = FetchType.EAGER)
    private List<Pierre> pierres = new ArrayList<>();

    public Commande(User client) {
        this.client = client;
        this.dateCommande = LocalDateTime.now();
    }
}
