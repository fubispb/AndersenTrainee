package internet_shop.products.not_food;

import internet_shop.application.ConnectBaseService;
import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Computer extends NotFood implements Serializable {

    private CurrencyStrategy currencyStrategy;
    private final String name = "Computer";
    private int boughtPrice = 250;
    private final long id;

    public Computer() {
        this.currencyStrategy = new DollarStrategy();
        id = ConnectBaseService.getProductIdByName(name);
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public void setExpiredDate(LocalDate date) {

    }

    @Override
    public String toString() {
        return name;
    }

}
