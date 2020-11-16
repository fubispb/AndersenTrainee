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
public class Cookies extends Food implements Serializable {

    private static final long serialVersionUID = -2024697128988416282L;
    private CurrencyStrategy currencyStrategy;
    private final String name = "Cookies";
    private int boughtPrice = 10;
    private final long id;
    @ExpiringProduct
    private LocalDate expiredDate;

    public Cookies() {
        this.currencyStrategy = new HryvniaStrategy();
        id = 3;
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
