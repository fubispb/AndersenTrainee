package solid;

//Liskov’s Substitution Principle
//Расширяя валидатор нельзя вместо возврата false начать бросать исключение
public class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (!item.isInStock() || !item.isPacked()) {
                return false;
                //throw new IllegalStateException(
                //        String.format("Order %d is not valid!", order.getId())
                //);
            }
        }

        return true;
    }
}
