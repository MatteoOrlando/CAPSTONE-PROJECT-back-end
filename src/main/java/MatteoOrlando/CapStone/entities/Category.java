package MatteoOrlando.CapStone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {
    // Setters
    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //relazione: una categoria/genere, puó avere piú titoli, ma solo un titolo puó appartenere ad un genere
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
