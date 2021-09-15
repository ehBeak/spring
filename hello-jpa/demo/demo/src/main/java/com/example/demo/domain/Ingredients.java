package com.example.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredients {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Long ghs;

    public Ingredients(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "INGRE_GHS")
    private List<Ghs> ghsArr = new ArrayList<>();

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

    public Long getGhs() {
        return ghs;
    }

    public void setGhs(Long ghs) {
        this.ghs = ghs;
    }
}
