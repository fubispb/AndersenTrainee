package internet_shop.application.model;

import lombok.Data;

@Data
public class Product implements Comparable{

    private long id;
    private String name;
    private int price;


    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        return (int) id;
    }
}
