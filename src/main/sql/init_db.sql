DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS billing_info;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS creditcard;
DROP TABLE IF EXISTS paypal;

CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(20) NOT NULL,
                        password VARCHAR(100) NOT NULL
);

CREATE TABLE billing_info (
                      id serial PRIMARY KEY,
                      user_id integer REFERENCES  users(id) NOT NULL,
                      name VARCHAR(60) NOT NULL,
                      phone_number VARCHAR(40) NOT NULL,
                      shipping_address VARCHAR(100) NOT NULL,
                      billing_address VARCHAR(100) NOT NULL,
                      title VARCHAR(40) NOT NULL
);

CREATE TABLE product (
                      id serial PRIMARY KEY,
                      name VARCHAR(40) NOT NULL,
                      price INTEGER NOT NULL,
                      currency VARCHAR(10) NOT NULL,
                      category_id integer NOT NULL,
                      supplier_id integer NOT NULL
);

CREATE TABLE category (
                         id serial PRIMARY KEY,
                         name VARCHAR(40),
                         department VARCHAR(40),
                         description VARCHAR(256)
);

CREATE TABLE supplier (
                          id serial PRIMARY KEY,
                          name VARCHAR(40),
                          description VARCHAR(256)
);

CREATE TABLE cart (
                          id serial PRIMARY KEY,
                          user_id INTEGER REFERENCES users(id),
                          product_id integer references product(id)
);

CREATE TABLE orders (
                      id serial PRIMARY KEY,
                      user_id integer REFERENCES users(id),
                      product_id integer references product(id)
);

CREATE TABLE creditcard (
                      id serial PRIMARY KEY,
                      user_id INTEGER REFERENCES users(id),
                      card_number VARCHAR(40) NOT NULL,
                      card_holder VARCHAR(60) NOT NULL,
                      exp_year VARCHAR(5) NOT NULL,
                      exp_month VARCHAR(5) NOT NULL,
                      cvv VARCHAR(3) NOT NULL
);

CREATE TABLE paypal (
                      id serial PRIMARY KEY,
                      user_id INTEGER REFERENCES users(id),
                      username VARCHAR(60) NOT NULL,
                      password VARCHAR(60) NOT NULL
);