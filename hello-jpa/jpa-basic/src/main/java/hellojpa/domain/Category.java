package hellojpa.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Long id;
    private String name;
    private List<Item> items = new ArrayList<>();

}
