package internet_shop.products.food;

import internet_shop.application.ConnectBaseService;
import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.DollarStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Coffee extends Food implements Serializable {

    private CurrencyStrategy currencyStrategy;
    private final String name = "Coffee";
    private int boughtPrice = 80;
    private final long id;
    @ExpiringProduct
    private LocalDate expiredDate;

    public Coffee() {
        this.currencyStrategy = new DollarStrategy();
        id = ConnectBaseService.getProductIdByName(name);
    }

    @Override
    public void setExpiredDate(LocalDate date) {
        this.expiredDate = date;
    }

    @Override
    public double getPrice() {
        return currencyStrategy.multiply(boughtPrice, currencyStrategy.getCourse(), currencyStrategy.getMultiplicity());
    }

    @Override
    public String toString() {
        return name;
    }

}
