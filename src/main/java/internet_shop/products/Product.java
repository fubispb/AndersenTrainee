package internet_shop.products;


import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
public abstract class Product implements Serializable {

    private static final long serialVersionUID = -7565655957307139235L;
    public static List<String> listOfProducts = Arrays.asList("Apple", "Coffee", "Cookies", "Chair", "Computer", "Table", "Parmalat");

    String name;
    int price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public  void setPrice(int price) {
        this.price = price;
    }

    public abstract void setExpiredDate(LocalDate date);

    public abstract long getId();


}
