package hello.core2.discount;

import hello.core2.member.Member;
import hello.core2.order.Order;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
