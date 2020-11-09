package internet_shop.products.not_food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Table extends NotFood implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Table";
    int boughtPrice = 70;

    public Table() {
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
