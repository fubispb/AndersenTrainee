package internet_shop.application.DAO;

import internet_shop.application.ConnectBaseService;
import internet_shop.application.model.Product;
import internet_shop.application.service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BucketDAO {
    ProductService productService;

    public Map<Product, Integer> getUserBucketById(long id) {
        Map<Product, Integer> bucket = new HashMap<>();
        productService = new ProductService();
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT productd_id, count " +
                    "FROM buckets " +
                    "WHERE users_id = '" + id + "';");
            while (rs.next()) {
                Product product = productService.getProductById(rs.getLong(2));
                int count = rs.getInt(3);
                bucket.put(product, count);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bucket;

    }

}
