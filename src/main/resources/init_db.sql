DROP TABLE IF EXISTS buckets;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS info_table;


CREATE TABLE users
(
  `id`                      BIGINT NOT NULL AUTO_INCREMENT,
  `name`                    VARCHAR(100) NOT NULL,
  PRIMARY KEY               (`id`)
);

CREATE TABLE orders
(
  `id`                      BIGINT NOT NULL AUTO_INCREMENT,
  `users_id`                BIGINT NOT NULL,
  `processed`               TINYINT NULL,
  `data`                    INT NULL,
  `date_created`            TIMESTAMP NULL DEFAULT NOW(),
  PRIMARY KEY               (`id`),
  FOREIGN KEY               (users_id)
  REFERENCES                users (id)
  ON DELETE                 NO ACTION
  ON UPDATE                 NO ACTION
);

CREATE TABLE products
(
  `id`                      BIGINT NOT NULL,
  `name`                    VARCHAR(100) NOT NULL,
  `price`                   INT NULL,
  PRIMARY KEY               (`id`)
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
        REFERENCES                products (id)
        ON DELETE                 NO ACTION
        ON UPDATE                 NO ACTION
);

CREATE TABLE info_table
(
  `id`                      BIGINT NOT NULL AUTO_INCREMENT,
  `users_id`                BIGINT NOT NULL,
  `order_created`           TIMESTAMP NULL DEFAULT NOW(),
  `orders_id`               BIGINT NULL,
  `data`                    INT NULL,
  `processed`               TINYINT NULL,
  PRIMARY KEY               (`id`)
);

# CREATE DEFINER = CURRENT_USER TRIGGER `orders_AFTER_INSERT`
# AFTER INSERT ON `orders` FOR EACH ROW
# BEGIN
# INSERT INTO info_table SET
#                              orders_id = NEW.id,
#                              users_id = NEW.users_id,
#                              order_created = TIMESTAMP(NOW()),
#                              `data` = NEW.`data`,
#                              processed = NEW.processed;
# END

# USE `my_shop`;
# DROP procedure IF EXISTS `info_user`;
#
# DELIMITER $$
# USE `my_shop`$$
# CREATE PROCEDURE `new_procedure` (OUT param1 TIMESTAMP, OUT param2 BIGINT, OUT param3 INT)
# BEGIN
#     SELECT COUNT(*) INTO param1 FROM info_table.order_created;
#     SELECT COUNT(*) INTO param2 FROM info_table.orders_id;
#     SELECT COUNT(*) INTO param3 FROM info_table.`data`;
# END$$
#
# DELIMITER ;
