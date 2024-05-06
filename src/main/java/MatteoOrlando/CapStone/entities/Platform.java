package MatteoOrlando.CapStone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //relazione: piú prodotti (stesso titolo) possono essere presenti su piú piattaforme da gioco
    @ManyToMany
    @JoinTable(
            name = "platforms_products",
            joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    private Set<Product> products;

}
