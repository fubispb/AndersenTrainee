package internet_shop.products;


import java.util.Arrays;
import java.util.List;

public abstract class Product {

    public static List<String> listOfProducts = Arrays.asList("Apple", "Coffee", "Cookies", "Chair", "Computer", "Table");

    String name;
    int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
