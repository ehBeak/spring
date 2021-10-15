package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) //em.persist(parent)만해도 child가 자동으로 persist된다.
    private List<Child> childList = new ArrayList<>(); // 어노테이션 밑에 있는 것을..

    // 연관관계 편의 메소드
    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
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
}
