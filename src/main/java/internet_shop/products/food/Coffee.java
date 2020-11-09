package internet_shop.products.food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Coffee extends Food implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Coffee";
    int boughtPrice = 80;
    @ExpiringProduct
    public LocalDate expiredDate;


    public Coffee() {
        this.currencyStrategy = new DollarStrategy();
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public void setExpiredDate(LocalDate date) {

    }

}
