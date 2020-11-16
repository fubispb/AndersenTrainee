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
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Warehouse implements Serializable {

    private static final long serialVersionUID = -295023693329265315L;
    public List<Product> products;

    public Warehouse() {

        products = new ArrayList<>();
        Apple apple = new Apple();
        checkBeforeStock(apple);
        products.add(apple);

        Coffee coffee = new Coffee();
        checkBeforeStock(coffee);
        products.add(coffee);

        Cookies cookies = new Cookies();
        checkBeforeStock(cookies);
        products.add(cookies);

        Parmalat parmalat = new Parmalat();
        checkBeforeStock(parmalat);
        products.add(parmalat);

        Chair chair = new Chair();
        checkBeforeStock(chair);
        products.add(chair);

        Computer computer = new Computer();
        checkBeforeStock(computer);
        products.add(computer);

        Table table = new Table();
        checkBeforeStock(table);
        products.add(table);
    }

    private void checkBeforeStock(Product product) {
        for (Field field : product.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ExpiringProduct.class)) {
                field.setAccessible(true);
                try {
                    field.set(product, LocalDate.of(2020, 11, 30));
                } catch (IllegalAccessException e) {
                    log.error("Start log. " + e);
                }
            }
        }
    }
}