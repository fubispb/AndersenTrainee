package internet_shop.products.food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Apple extends Food implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Apple";
    int boughtPrice = 30;

    @ExpiringProduct
    private LocalDate expiredDate;

    public Apple() {
        this.currencyStrategy = new DollarStrategy();
    }

    @Override
    public void setExpiredDate(LocalDate date) {
        this.expiredDate = date;
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

}
