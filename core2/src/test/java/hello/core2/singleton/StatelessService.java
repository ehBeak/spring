package hello.core2.singleton;

public class StatelessService {
//    private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

}
