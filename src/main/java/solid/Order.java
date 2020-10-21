package solid;


// Stub for Order class
public class Order {
    private final long id;
    private final int count;
    private final String customerName;
    private final String customerEmail;

    public Order(long id, int count, String customerName, String customerEmail) {
        this.id = id;
        this.count = count;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public boolean isValid() {
        return false;
    }

    public Item[] getItems() {
        return new Item[0];
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
