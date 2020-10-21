package solid;

// Liskov’s Substitution Principle
// Extending the validator you can't start throwing an exception instead of returning false
public class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (!item.isInStock() || !item.isPacked()) {
                return false;
            }
        }

        return true;
    }
}
