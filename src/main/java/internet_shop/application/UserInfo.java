package internet_shop.application;

public class UserInfo {
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
