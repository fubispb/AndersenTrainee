package internet_shop.warehouse;

import internet_shop.products.ExpiringProduct;
import internet_shop.products.Product;
import internet_shop.products.food.Apple;
import internet_shop.products.food.Coffee;
import internet_shop.products.food.Cookies;
import internet_shop.products.milk.Parmalat;
import internet_shop.products.not_food.Chair;
import internet_shop.products.not_food.Computer;
import internet_shop.products.not_food.Table;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Warehouse implements Serializable {

    public Map<Product, Integer> products;

    public Warehouse() {
        this.products = new HashMap<>();
        Apple apple = new Apple();
        checkBeforeStock(apple);
        products.put(apple, 20);

        Coffee coffee = new Coffee();
        checkBeforeStock(coffee);
        products.put(coffee, 15);

        Cookies cookies = new Cookies();
        checkBeforeStock(cookies);
        products.put(cookies, 100);

        Parmalat parmalat = new Parmalat();
        checkBeforeStock(parmalat);
        products.put(parmalat, 40);

        Chair chair = new Chair();
        checkBeforeStock(chair);
        products.put(chair, 8);

        Computer computer = new Computer();
        checkBeforeStock(computer);
        products.put(computer, 7);

        Table table = new Table();
        checkBeforeStock(table);
        products.put(table, 3);
    }

    private void checkBeforeStock(Product product) {
        for (Field field : product.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ExpiringProduct.class)) {
                field.setAccessible(true);
                try {
                    field.set(product, "31.11.2020");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}