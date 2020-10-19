package solid;


//Заглушка класса товаров в заказе
public class Item {
    private String name;

    public boolean isInStock() {
        return false;
    }

    public boolean isPacked() {
        return false;
    }
}
