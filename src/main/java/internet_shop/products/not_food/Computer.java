package internet_shop.products.not_food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;

import java.io.Serializable;
import java.util.Objects;

public class Computer extends NotFood implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Computer";
    int boughtPrice = 250;

    public Computer() {
        this.currencyStrategy = new DollarStrategy();
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
