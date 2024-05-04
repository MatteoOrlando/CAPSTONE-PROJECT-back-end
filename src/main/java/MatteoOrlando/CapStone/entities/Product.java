package MatteoOrlando.CapStone.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    // relazione: piú prodotti possono appartenere ad una categoria
    @ManyToOne
    @JoinColumn(name = "category_id")
    private String category;

    // relazione: piú prodotti possono essere presenti su piú piattaforme
    @ManyToMany(mappedBy = "products")
    private Set<Platform> platforms;

    // relazione: un prodotto puó essere recensito da piú utenti piú volte,
    // quindi potenzialmente un prodotto puó avere piú recensioni associate
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;


    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
