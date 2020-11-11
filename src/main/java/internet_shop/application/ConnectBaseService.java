package internet_shop.application;

import internet_shop.products.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class ConnectBaseService {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/my_shop", "root", "QQferrari9900");
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
        statement.executeUpdate("" +
                "INSERT INTO orders (`order_id`, `users_id`, `sum`, `processed`)"
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
            ResultSet rs = statement.executeQuery("" +
                    "SELECT name " +
                    "FROM my_shop.users " +
                    "WHERE id = " + id + ";");
            while (rs.next()) result = rs.getString("name");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static long getUserIdByName(String userInput) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("" +
                    "SELECT id " +
                    "FROM my_shop.users " +
                    "WHERE name = '" + userInput + "';");
            while (rs.next()) result = rs.getLong("id");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static long getProductIdByName(String name) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("" +
                    "SELECT product_id " +
                    "FROM my_shop.products " +
                    "WHERE name = '" + name + "';");
            while (rs.next()) result = rs.getLong("product_id");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    private static long getLastOrderIdByUser(User user) {
        long result = 0;
        try {
            ResultSet rs = statement.executeQuery("" +
                    "SELECT max(order_id) " +
                    "FROM my_shop.orders " +
                    "WHERE users_id = '" + user.getId() + "';");
            while (rs.next()) result = rs.getLong(1);
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
        return result;
    }

    public static void userInfo(User user) {
        UserInfo userInfo = new UserInfo(user);
        StringBuilder result = new StringBuilder();
        List<Long> list = ConnectBaseService.getOrderNumbersByUser(user);
        for (Long numbers : list) {
            result.append(getOrderContainsById(numbers)).append("\n");
        }
        userInfo.orderContains = result.toString();
        CallableStatement call;
        try {
            call = connection.prepareCall("CALL info_user(?, ?, ?)");
            call.setLong(1, user.getId());
            call.execute();
            userInfo.orderCounts = call.getInt(2);
            userInfo.totalSum = call.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userInfo);
    }

    public static String getOrderContainsById(long id) {
        StringBuilder result = new StringBuilder();
        try {
            ResultSet rs = statement.executeQuery("" +
                    "SELECT name, count " +
                    "FROM order_products " +
                    "INNER JOIN products " +
                    "ON (products_id = product_id) " +
                    "WHERE order_id = '" + id + "';");
            result.append("Order #").append(id).append(" contains: \n");
            while (rs.next()) {
                result.append(rs.getString(1)).append(" ");
                result.append(rs.getInt(2)).append("\n");
            }
            rs = statement.executeQuery("" +
                    "SELECT order_sum " +
                    "FROM info_table " +
                    "WHERE order_id = '" + id + "';");
            rs.next();
            result.append("Order sum = ").append(rs.getInt(1)).append("\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static List<Long> getOrderNumbersByUser(User user) {
        List<Long> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("" +
                    "SELECT order_id " +
                    "FROM info_table " +
                    "WHERE users_id = '" + user.getId() + "';");
            while(rs.next()) {
                list.add(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
