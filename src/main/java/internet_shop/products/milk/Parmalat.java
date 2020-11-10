package internet_shop.products.milk;

import internet_shop.application.ConnectBaseService;
import internet_shop.currency.CurrencyStrategy;
import internet_shop.currency.HryvniaStrategy;
import internet_shop.products.ExpiringProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Parmalat extends Milk implements Serializable {

    private CurrencyStrategy currencyStrategy;
    private final String name = "Parmalat";
    private int boughtPrice = 25;
    private final long id;
    @ExpiringProduct
    private LocalDate expiredDate;

    public Parmalat() {
        this.currencyStrategy = new HryvniaStrategy();
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
