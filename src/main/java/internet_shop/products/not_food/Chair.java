package internet_shop.products.not_food;

import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Chair extends NotFood implements Serializable {

    CurrencyStrategy currencyStrategy;
    final String name = "Chair";
    int boughtPrice = 50;

    public Chair() {
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
