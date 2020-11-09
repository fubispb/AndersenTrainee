package internet_shop.products.not_food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Computer extends NotFood implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Computer";
    int boughtPrice = 250;

    public Computer() {
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
