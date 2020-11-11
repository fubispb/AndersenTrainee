DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS buckets;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS info_table;
DROP PROCEDURE IF EXISTS `info_user`;


CREATE TABLE users
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100) NOT NULL,
    PRIMARY KEY               (`id`)
);

CREATE TABLE orders
(
    `order_id`                BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `processed`               TINYINT NULL,
    `sum`                     INT NULL,
    `date_created`            TIMESTAMP NULL DEFAULT NOW(),
    PRIMARY KEY               (`order_id`),
    INDEX                     `fk_users_idx` (`users_id` ASC) VISIBLE,
    CONSTRAINT                `fk_users`
    FOREIGN KEY               (`users_id`)
    REFERENCES                `users` (`id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE products
(
    `product_id`              BIGINT NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100) NOT NULL,
    `price`                   INT NULL,
    PRIMARY KEY               (`product_id`)
);

CREATE TABLE order_products (
    `order_id`                BIGINT NOT NULL,
    `products_id`             BIGINT NULL,
    `count`                   INT NULL,
    INDEX                     `fk_products_id_idx` (`products_id` ASC) VISIBLE,
    CONSTRAINT                `fk_order_id`
    FOREIGN KEY               (`order_id`)
    REFERENCES                orders (`order_id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION,
    CONSTRAINT                `fk_products_id`
    FOREIGN KEY               (`products_id`)
    REFERENCES                products (`product_id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE buckets
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `products_id`             BIGINT NULL,
    PRIMARY KEY               (`id`),
    FOREIGN KEY               (users_id)
    REFERENCES                users (id)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION,
    FOREIGN KEY               (products_id)
    REFERENCES                products (product_id)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE info_table
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `order_id`                BIGINT NULL,
    `processed`               TINYINT NULL,
    `order_sum`               INT NULL,
    `order_created`           TIMESTAMP NULL DEFAULT NOW(),
    PRIMARY KEY               (`id`)
);

INSERT INTO users (`name`) VALUES ('admin');
INSERT INTO users (`name`) VALUES ('Bob');
INSERT INTO products (`name`, `price`) VALUES ('Apple', '30');
INSERT INTO products (`name`, `price`) VALUES ('Coffee', '80');
INSERT INTO products (`name`, `price`) VALUES ('Cookies', '10');
INSERT INTO products (`name`, `price`) VALUES ('Parmalat', '25');
INSERT INTO products (`name`, `price`) VALUES ('Chair', '50');
INSERT INTO products (`name`, `price`) VALUES ('Computer', '250');
INSERT INTO products (`name`, `price`) VALUES ('Table', '70');


DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `orders_AFTER_INSERT`
AFTER INSERT ON `orders` FOR EACH ROW
BEGIN
INSERT INTO info_table SET
                             order_id = NEW.order_id,
                             users_id = NEW.users_id,
                             order_created = NEW.`date_created`,
                             processed = NEW.processed,
                             order_sum = NEW.sum;
END$$
DELIMITER ;


USE `my_shop`;
DROP procedure IF EXISTS `info_user`;
DELIMITER $$
USE `my_shop`$$
CREATE PROCEDURE `info_user`(IN id BIGINT, OUT param1 INT, OUT param2 INT)
BEGIN
    SELECT count(order_id) INTO param1 FROM info_table WHERE users_id = id;
    SELECT sum(order_sum) INTO param2 FROM info_table WHERE users_id = id;
END$$
DELIMITER ;
