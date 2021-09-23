package yoso.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GHS")
public class Ghs {

    @Id @GeneratedValue
    @Column(name = "GHS_ID")
    private Long id;

    private String category;
    private String ghsClass;

    @OneToMany(mappedBy = "ghs")
    private List<IngreGhs> ingreGhsList = new ArrayList<>();

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

    public List<IngreGhs> getIngreGhsList() {
        return ingreGhsList;
    }

    public void setIngreGhsList(List<IngreGhs> ingreGhsList) {
        this.ingreGhsList = ingreGhsList;
    }
}
