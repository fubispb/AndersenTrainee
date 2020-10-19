package solid;


//Заглушка класса Заказ
public class Order {
    private long id;
    private int count;
    private String customerName;
    private String customerEmail;

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
