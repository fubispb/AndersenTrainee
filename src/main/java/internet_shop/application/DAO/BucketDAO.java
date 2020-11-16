package internet_shop.application.DAO;

import internet_shop.application.ConnectBaseService;
import internet_shop.application.model.Product;
import internet_shop.application.service.ProductService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class BucketDAO {
    private ProductService productService;

    public Map<Product, Integer> getUserBucketById(long id) {
        Map<Product, Integer> bucket = new TreeMap<>();
        productService = ProductService.getInstance();
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT products_id, sum(count) " +
                    "FROM buckets " +
                    "WHERE users_id = '" + id + "' " +
                    "GROUP BY products_id;");
            while (rs.next()) {
                Product product = productService.getProductById(rs.getLong(1));
                int count = rs.getInt(2);
                bucket.put(product, count);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bucket;
    }

    public void deleteFromUserBucket(long idUser, long idProduct) {
        try {
            ConnectBaseService.connect();
            ConnectBaseService.statement.executeUpdate("" +
                    "DELETE FROM buckets " +
                    "WHERE (products_id = '" + idProduct + "' " +
                    "AND users_id = '" + idUser + "');");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }
}
