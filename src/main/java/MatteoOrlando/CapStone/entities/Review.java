package MatteoOrlando.CapStone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "reviews")
public class Review {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String content;

    @Setter
    @Getter
    @Column(nullable = false)
    private int rating;

    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    public Review() {
    }


    public String comment() {
        return content;

    }

    public int rating() {
        return rating;
    }

    public Long userId() {
            return user.getId();
    }


}
