package internet_shop.application.model;

import internet_shop.application.User;
import lombok.Data;

import java.util.Map;

@Data
public class Bucket {

    private long id;
    private User user;
    private Map<Product, Integer> userBucket;

    public Bucket(long id, User user, Map<Product, Integer> userBucket) {
        this.id = id;
        this.user = user;
        this.userBucket = userBucket;
    }
}
