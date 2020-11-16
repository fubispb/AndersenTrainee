package internet_shop.products.food;

import internet_shop.application.ConnectBaseService;
import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Apple extends Food implements Serializable {

    private static final long serialVersionUID = 5311393141525536933L;
    private CurrencyStrategy currencyStrategy;
    private final String name = "Apple";
    private int boughtPrice = 30;
    private final long id;
    @ExpiringProduct
    private LocalDate expiredDate;

    public Apple() {
        this.currencyStrategy = new HryvniaStrategy();
        id = 1;
    }

    @Override
    public void setExpiredDate(LocalDate date) {
        this.expiredDate = date;
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public String toString() {
        return name;
    }
}
