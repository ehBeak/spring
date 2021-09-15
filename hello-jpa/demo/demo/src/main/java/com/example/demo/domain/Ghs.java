package com.example.demo.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ghs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull private String category;
    @NotNull private String classes;

    public Ghs(Long id, String category, String classes) {
        this.id = id;
        this.category = category;
        this.classes = classes;
    }

    @ManyToMany(mappedBy = "ghsArr")
    private List<Ingredients> ingredientsArr = new ArrayList<>();

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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
