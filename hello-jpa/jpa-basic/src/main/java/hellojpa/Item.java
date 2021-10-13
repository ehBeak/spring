package hellojpa;

import javax.persistence.*;

// 상속매핑
/*
 * @Inheritance(strategy = "")
 *  1. SINGLE_TYPE : 단일 테이블 전략(default)
 *  2. JOINED : 조인 전략
 *  3. TABLE_PER_CLASS : 각각 테이블 생성 전략
 *
 * DTYPE : item을 상속한 entity명이 DTYPE 값에 들어감
 *  -> 각각의 entity는 @DiscriminateValue로 열에 들어갈 entity명을 바꿀 수 있음*/

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 부모클래스에 조인 전략 명시
@DiscriminatorColumn // DTYPE colum 생성(name => 열명 바꿀 수 있음)
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
