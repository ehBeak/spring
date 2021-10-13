package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
    joinColumns = @JoinColumn(name = "CATEGORY_ID"),
    inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();



    /* 셀프로 매핑 가능 */
    //  자식(현재 구현 클래스) 입장에서 부모가 하나..?
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // 현재 구현 클래스가 부모인 입장에서 자식참조
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();



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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
