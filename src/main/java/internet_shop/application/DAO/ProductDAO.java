package internet_shop.application.DAO;

import internet_shop.application.ConnectBaseService;
import internet_shop.application.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getGeneralProductList() {
        List<Product> list = new ArrayList<>();
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT product_id, name, price " +
                    "FROM products;");
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getInt("price"));
                list.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertInUserBucket(long id, int count) {
        try {
            ConnectBaseService.connect();
            PreparedStatement preparedStatement = ConnectBaseService.connection.prepareStatement("" +
                    "INSERT INTO buckets (users_id, products_id, count)" +
                    "VALUES (?, ?, ?);");
            preparedStatement.setLong(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.setLong(3, count);
            preparedStatement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }
}
