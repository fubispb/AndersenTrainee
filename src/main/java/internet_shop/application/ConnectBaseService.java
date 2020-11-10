package internet_shop.application;

import internet_shop.products.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Map;

@Slf4j
public class ConnectBaseService {

    private static Connection connection;
    private static Statement statement;


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/my_shop", "admin", "123456");
        statement = connection.createStatement();
        System.out.println("Connection to data base established.");
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
    }

    //Create order in base and return order id.
    public static long confirmOrderAndGetId(Map<Product, Integer> order, User user, double amount) throws SQLException {
        long newId = getLastOrderIdByUser(user) + 1;
        statement.executeUpdate("INSERT INTO orders (`order_id`, `users_id`, `sum`, `processed`)"
                + "VALUES (" + newId + ", " + user.getId() + ", " + amount + ",  1)");
        connection.setAutoCommit(false);
        for (Map.Entry<Product, Integer> set : order.entrySet()) {
            String query = String.format("INSERT INTO order_products (`order_id`, `products_id`, `count`)"
                    + "VALUES (%d, %d, %d);", newId, set.getKey().getId(), set.getValue());
            statement.executeUpdate(query);
        }
        connection.setAutoCommit(true);
        return newId;
    }

    public static String getUserByID(long id) {
        String result = null;
        try {
            ResultSet rs = statement.executeQuery("SELECT name FROM my_shop.users WHERE id = " + id + ";");
            while (rs.next()) result = rs.getString("name");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static long getUserIdByName(String userInput) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT id FROM my_shop.users WHERE name = '" + userInput + "';");
            while (rs.next()) result = rs.getLong("id");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static long getProductIdByName(String name) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT product_id FROM my_shop.products WHERE name = '" + name + "';");
            while (rs.next()) result = rs.getLong("product_id");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    private static long getLastOrderIdByUser(User user) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT max(order_id) FROM my_shop.orders WHERE users_id = '" + user.getId() + "';");
            while (rs.next()) result = rs.getLong(1);
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static void userInfo(User user) {
        //TODO
        CallableStatement call;
        try {
            call = connection.prepareCall("CALL info_user(?)");
            call.setLong(1, user.getId());
            call.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
