package internet_shop.products.food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Cookies extends Food implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Cookies";
    int boughtPrice = 10;
    @ExpiringProduct
    public LocalDate expiredDate;

    public Cookies() {
        this.currencyStrategy = new HryvniaStrategy();
    }


    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public void setExpiredDate(LocalDate date) {

    }

}
