package internet_shop.application.service;

import internet_shop.application.DAO.BucketDAO;
import internet_shop.application.DAO.ProductDAO;
import internet_shop.application.model.Product;

import java.util.Map;
import java.util.Objects;

public class BucketService {

    private BucketDAO bucketDAO = new BucketDAO();
    private static BucketService instance;

    private BucketService() {}

    public static BucketService getInstance() {
        if (Objects.isNull(instance)) instance = new BucketService();
        return instance;
    }

    public Map<Product, Integer> getBucketByUserId(long id) {
        return bucketDAO.getUserBucketById(id);
    }

    public void insertInBucketByProductId(long id, int count) {
        bucketDAO.insertInUserBucket(id, count);
    }

    public void removeProductFromBucketByProductId(long idUser, long idProduct) {
        bucketDAO.deleteFromUserBucket(idUser, idProduct);
    }
}
