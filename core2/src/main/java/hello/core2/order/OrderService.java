package hello.core2.order;

import hello.core2.member.Member;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
