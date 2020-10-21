package solid;

// Single Responsibility Principle
// Taking out the logic for save an order to a separate class
public class MySQLOrderRepository implements OrderRepository {

    @Override
    public boolean save(Order order) {

        return true;
    }
}
