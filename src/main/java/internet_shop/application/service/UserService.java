package internet_shop.application.service;

import internet_shop.application.DAO.UserDAO;
import internet_shop.application.model.Product;
import internet_shop.application.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public void deleteProductFromBucketByName(User user, Product product) {
        user.bucket.remove(product);
    }

}
