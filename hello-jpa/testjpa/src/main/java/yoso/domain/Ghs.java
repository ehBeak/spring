package yoso.domain;

import javax.persistence.*;

@Entity
@Table(name = "GHS")
public class Ghs {

    @Id @GeneratedValue
    @Column(name = "GHS_ID")
    private Long id;

    private String category;
    private String ghsClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGhsClass() {
        return ghsClass;
    }

    public void setGhsClass(String ghsClass) {
        this.ghsClass = ghsClass;
    }
}
