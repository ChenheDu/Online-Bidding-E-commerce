DROP DATABASE IF EXISTS book;

CREATE DATABASE book;

USE book;

CREATE TABLE t_user(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(32) NOT NULL,
    `email` VARCHAR(200)

);

INSERT INTO t_user(`username`,`password`,`email`) VALUES('admin','admin','admin@atguigu.com');




USE book;

CREATE TABLE t_book(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100),
    `price` DECIMAL(11,2),
    `author` VARCHAR(100),
    `sales` INT,
    `stock` INT,
    `img_path` VARCHAR(200)
);


## insert
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Monarchy' , 'David' , 80 , 9999 , 9 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'History of Western Philosophy' , 'Tom' , 78.5 , 6 , 13 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Mac air 2017 laptop' , 'Long' , 68, 99999 , 52 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Bluetooth earphone' , 'Lucy' , 16, 1000 , 50 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'C++ programming' , 'Taylor' , 45.5 , 14 , 95 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Fried Rice Recipe' , 'Xingxing' , 9.9, 12 , 53 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Instant pot' , 'Wu' , 66.5, 125 , 535 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Shoebox' , 'Yeveet' , 99.5 , 47 , 36 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Snow shovel' , 'Ting' , 9.9 , 85 , 95 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'cocos2d-x Gaming' , 'Jerry' , 49, 52 , 62 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'coffee pot' , 'Tan' , 28 , 52 , 74 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Lua language programming' , 'Ray' , 51.5 , 48 , 82 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Msi PC host' , 'Linda' , 12, 19 , 9999 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Msi laptop' , 'Hua' , 33.05 , 22 , 88 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Operationg system' , 'Harry' , 133.05 , 122 , 188 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Algorithm' , 'Tod' , 173.15 , 21 , 81 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Logitech mouse' , 'Lee' , 99.15 , 210 , 810 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Disinfection water' , 'Johns' , 69.15 , 210 , 810 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'Mask' , 'Guo' , 89.15 , 20 , 10 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)
VALUES(NULL , 'bicycle' , 'Gang' , 88.15 , 20 , 80 , 'static/img/default.jpg');



## check
SELECT id,NAME,author,price,sales,stock,img_path FROM t_book;

SELECT COUNT(*) FROM t_book WHERE price BETWEEN 10 AND 50;

SELECT * FROM t_book LIMIT 0, 4;`mysql``book`

DELETE FROM t_book WHERE id=76;

SELECT * FROM t_book WHERE price BETWEEN 10 AND 50 ORDER BY price LIMIT 0, 4;



USE book;

CREATE TABLE t_order(
    `order_id` VARCHAR(50) PRIMARY KEY,
    `create_time` DATETIME,
    `price` DECIMAL(11,2),
    `status` INT,
    `user_id` INT,
    FOREIGN KEY(`user_id`) REFERENCES t_user(`id`)

);

CREATE TABLE t_order_item(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100),
    `count` INT,
    `price` DECIMAL(11,2),
    `total_price` DECIMAL(11.2),
    `order_id` VARCHAR(50),
    FOREIGN KEY(`order_id`) REFERENCES t_order(`order_id`)
);