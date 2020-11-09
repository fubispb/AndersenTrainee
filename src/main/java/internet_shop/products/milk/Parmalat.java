package internet_shop.products.milk;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import internet_shop.products.ExpiringProduct;

import java.io.Serializable;
import java.util.Objects;


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
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public void setExpiredDate(String date) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return Objects.nonNull(o) && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, boughtPrice);
    }

    @Override
    public String toString() {
        return name;
    }

}
