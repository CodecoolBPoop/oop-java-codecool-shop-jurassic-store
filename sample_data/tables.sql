DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_cat;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;

CREATE TABLE product_cat (
                           id serial not null,
                           description varchar,
                           department varchar,
                           name varchar,
                           primary key (id)
);

CREATE TABLE suppliers (
                         id serial not null,
                         name varchar,
                         description varchar,
                         primary key (id)
);

CREATE TABLE products (
                        id serial NOT NULL,
                        supplier_id int,
                        category_id int,
                        name varchar,
                        description varchar,
                        price float,
                        currency varchar,
                        PRIMARY KEY (id),
                        foreign key (supplier_id) references suppliers(id),
                        foreign key (category_id) references product_cat(id)
);


CREATE TABLE users (
                     id serial not null,
                     name varchar,
                     password varchar,
                     primary key (id)
);

CREATE TABLE orders (
                      id int not null ,
                      user_id int,
                      product_id int,
                      quantity int,
                      foreign key (user_id) references users(id),
                      foreign key (product_id) references products(id)
);

ALTER SEQUENCE products_id_seq START WITH 1;
ALTER SEQUENCE product_cat_id_seq START WITH 1;
ALTER SEQUENCE suppliers_id_seq START WITH 1;
ALTER SEQUENCE users_id_seq START WITH 1;

ALTER SEQUENCE products_id_seq RESTART WITH 1;
ALTER SEQUENCE product_cat_id_seq RESTART WITH 1;
ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;