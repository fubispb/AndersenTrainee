package internet_shop.products.food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import internet_shop.products.ExpiringProduct;

import java.io.Serializable;
import java.util.Objects;


public class Apple extends Food implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Apple";
    int boughtPrice = 30;

    @ExpiringProduct
    private String expiredDate;

    public Apple() {
        this.currencyStrategy = new DollarStrategy();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setExpiredDate(String date) {
        this.expiredDate = date;
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public void setPrice(int price) {
        this.boughtPrice = price;
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
