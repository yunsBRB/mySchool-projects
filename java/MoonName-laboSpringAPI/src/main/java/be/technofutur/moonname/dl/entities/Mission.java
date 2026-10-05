package be.technofutur.moonname.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private LocalDate dateDepart;

    public Mission(String nom, LocalDate dateDepart) {
        this.nom = nom;
        this.dateDepart = dateDepart;
    }
}
