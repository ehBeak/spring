package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // # 연관관계 주인에 꼭 값을 넣기(List에 넣는 것이 아님)
    @ManyToOne // 외래키가 있는 곳(N)으로 연관관계 주인을 하기
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Temporal(TemporalType.DATE)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 후에 setOrderItems(a)라도 해도 DB에 적용되지 않음
    @OneToMany(mappedBy = "orders") // mappedBy는 읽기전용만 가능
    private List<OrderItem> orderItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void changeOrder(Member member) {
        this.member = member;
        member.getOrders().add(this); // #연관관계 편의 메소드
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
