package internet_shop.products.not_food;

import internet_shop.application.ConnectBaseService;
import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Chair extends NotFood implements Serializable {

    private CurrencyStrategy currencyStrategy;
    private final String name = "Chair";
    private int boughtPrice = 25;
    private final long id;

    public Chair() {
        this.currencyStrategy = new HryvniaStrategy();
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
