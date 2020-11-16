package internet_shop.application.DAO;

import internet_shop.application.ConnectBaseService;
import internet_shop.application.model.User;
import internet_shop.application.service.BucketService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private BucketService bucketService;

    public User getUserById(long id) {
        bucketService = BucketService.getInstance();
        String name = null;
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT name " +
                    "FROM users " +
                    "WHERE id = '" + id + "';");
            rs.next();
            name = rs.getString("name");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new User(id, name, bucketService.getBucketByUserId(id));

    }
}
