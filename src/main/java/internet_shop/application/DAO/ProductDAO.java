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
}
