package yoso.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INGREDITNES")
public class Ingredient {

    @Id @GeneratedValue
    @Column(name = "INGRE_ID")
    private Long id;

    private String name;
    private String type;

    @OneToMany(mappedBy = "ingredient")
    private List<IngreGhs> ingreGhsList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<IngreGhs> getIngreGhsList() {
        return ingreGhsList;
    }

    public void setIngreGhsList(List<IngreGhs> ingreGhsList) {
        this.ingreGhsList = ingreGhsList;
    }
}
