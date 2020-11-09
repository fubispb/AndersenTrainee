package internet_shop.products.milk;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Parmalat extends Milk implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Parmalat";
    int boughtPrice = 25;
    @ExpiringProduct
    public String expiredDate;

    public Parmalat() {
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
