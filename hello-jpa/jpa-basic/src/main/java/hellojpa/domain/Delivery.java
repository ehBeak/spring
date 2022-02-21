package hellojpa.domain;

public class Delivery {
    private Long id;
    private Order order;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
