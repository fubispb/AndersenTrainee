package solid;


//Interface Segregation Principle
//Разделяем 1 общий интерфейс на 2 интерфейса отправки подтверждения и сохранения
public interface OrderRepository {

    boolean save(Order order);

}
