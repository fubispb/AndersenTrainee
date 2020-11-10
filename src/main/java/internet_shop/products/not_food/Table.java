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
public class Table extends NotFood implements Serializable {

    private CurrencyStrategy currencyStrategy;
    private final String name = "Table";
    private int boughtPrice = 70;
    private final long id;

    public Table() {
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
