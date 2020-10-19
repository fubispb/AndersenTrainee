package solid;

//Single Responsibility Principle
// Выносим логику по сохранению заказа в отдельный класс
public class MySQLOrderRepository implements OrderRepository {

    @Override
    public boolean save(Order order) {
        //MySqlConnection connection = new MySqlConnection("database.url");
        // сохраняем заказ в базу данных

        return true;
    }
}
