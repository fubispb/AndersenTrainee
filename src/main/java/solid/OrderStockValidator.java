package solid;

//Валидатор все ли товары заказа находятся на складе
public class OrderStockValidator {

    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (!item.isInStock()) {
                return false;
            }
        }

        return true;
    }
}
