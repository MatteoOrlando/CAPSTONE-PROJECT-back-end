package MatteoOrlando.CapStone.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private int quantita;

    @Column(nullable = false)
    private double prezzo;


    // Costruttore vuoto
    public Order() {
    }

}

