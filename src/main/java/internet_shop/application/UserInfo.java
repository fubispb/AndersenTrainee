package internet_shop.application;

import internet_shop.products.Product;

import java.util.Map;

public class UserInfo {
    //TODO
    User user;
    String orderContains;
    int orderCounts;
    int totalSum;

    public UserInfo(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo of " +
                "user " + user.getName() +
                "\n" + orderContains +
                "orderCounts=" + orderCounts +
                ", totalSum=" + totalSum;
    }
}
