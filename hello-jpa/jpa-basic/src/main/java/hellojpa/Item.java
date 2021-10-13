package hellojpa;

import javax.persistence.*;

// 상속매핑
/*
 * @Inheritance(strategy = "")
 *  1. SINGLE_TYPE : 단일 테이블 전략(default)
 *  2. JOINED : 조인 전략
 *  3. TABLE_PER_CLASS : 각각 테이블 생성 전략 -> item테이블 생성 안하고 분리시켜서 알아서함
 *
 * DTYPE : item을 상속한 entity명이 DTYPE 값에 들어감
 *  -> 각각의 entity는 @DiscriminateValue로 열에 들어갈 entity명을 바꿀 수 있음
 *  -> single table전략에서는 DTYPE 적용해야함(구분해야 하니까)
 *      ==> 여기서는 @DiscriminatorColumn이 없어도 적용된다.*/

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 부모클래스에 단일 클래스 전략 명시
//@DiscriminatorColumn // 필요 없음 -> 테이블이 다르니까 구분할 필요 없음
public abstract class Item { // 추상 클래스로 만들기!

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
