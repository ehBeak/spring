package yoso.domain;

import javax.persistence.*;

@Entity
@Table(name = "INGRE_GHS")
public class IngreGhs {

    @Id @GeneratedValue
    @Column(name = "INGRE_GHS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "INGRE_ID")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "GHS_ID")
    private Ghs ghs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ghs getGhs() {
        return ghs;
    }

    public void setGhs(Ghs ghs) {
        this.ghs = ghs;
    }
}
