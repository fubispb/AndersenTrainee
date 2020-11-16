package internet_shop.application.service;

import internet_shop.application.DAO.BucketDAO;
import internet_shop.application.model.Product;

import java.util.Map;

public class BucketService {

    BucketDAO bucketDAO;

    public Map<Product, Integer> getBucketByUserId(long id) {
        return bucketDAO.getUserBucketById(id);
    }
}
