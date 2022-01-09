package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @DisplayName("상품 주문")
    public void 상품주문() throws Exception {
        //given
        Member member = creatMember();
        Book book = createBook("시골 JPA", 10000, 10);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());//상품 주문시 상태는 ORDER
        assertThat(1).isEqualTo(getOrder.getOrderItems().size());//주문한 상품 종류 수가 정확해야 한다.
        assertThat(10000 * orderCount).isEqualTo(getOrder.getTotalPrice());//주문 가격은 가격 * 수량이다.
        assertThat(8).isEqualTo(book.getStockQuantity());//주문 수량만큼 재고가 줄어야 한다.
    }



    @Test
    @DisplayName("재고 수량 초과")
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = creatMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when //then
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount));
    }

    @Test
    @DisplayName("주문 취소")
    public void 주문취소() throws Exception {
        //given
        Member member = creatMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());//주문 취소시 상태는 CANCEL이다.
        assertThat(10).isEqualTo(item.getStockQuantity());//주문이 취소된 상품은 그만큼 재고가 증가해야한다.
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member creatMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}