DROP TABLE IF EXISTS billing_info;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS creditcard;
DROP TABLE IF EXISTS paypal;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE billing_info (
    id serial PRIMARY KEY,
    user_id integer REFERENCES  users(id) NOT NULL,
    name VARCHAR(60) NOT NULL,
    phone_number VARCHAR(40) NOT NULL,
    shipping_address VARCHAR(100) NOT NULL,
    billing_address VARCHAR(100) NOT NULL
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

CREATE TABLE product (
    id serial PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    price INTEGER NOT NULL,
    currency VARCHAR(10) NOT NULL,
    description VARCHAR(256),
    category_id integer NOT NULL,
    supplier_id integer NOT NULL
);

CREATE TABLE cart (
    id serial PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    cart_items VARCHAR(500)
);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    user_id integer REFERENCES users(id),
    order_info VARCHAR(1000)
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

INSERT INTO users (id, email, password) VALUES (1, 'kispistashop@gmail.com', '3f7bf8614ecde909b9b294c4043d91518c85b9cc24ef705167d2d0fd81332cea39616615d88a2d16e98f90c8993025dac85efa3aae0aa1a59105fd3ddf5a57db');
INSERT INTO users (id, email, password) VALUES (2, 'bogar.jozsef@gmail.com', '3f7bf8614ecde909b9b294c4043d91518c85b9cc24ef705167d2d0fd81332cea39616615d88a2d16e98f90c8993025dac85efa3aae0aa1a59105fd3ddf5a57db');

INSERT INTO billing_info (id, user_id, name, phone_number, shipping_address, billing_address) VALUES (1, 1, 'Kis Pista', '061234567', 'Hal utca 1', 'Hal utca 1');
INSERT INTO billing_info (id, user_id, name, phone_number, shipping_address, billing_address) VALUES (2, 2, 'Nagy Geza', '069876543', 'Fa utca 2', 'Fa utca 2');

INSERT INTO category (id, name, department, description) VALUES (1, 'Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO category (id, name, department, description) VALUES (2, 'Phone', 'Hardware', 'A device for calling and personal use when moving.');
INSERT INTO category (id, name, department, description) VALUES (3, 'Weapon', 'Military', 'It can save your life.');
INSERT INTO category (id, name, department, description) VALUES (4, 'Gardening', 'Household', 'Product for outside work.');

INSERT INTO supplier (id, name, description) VALUES (1, 'Amazon', 'Digital content and services');
INSERT INTO supplier (id, name, description) VALUES (2, 'Lenovo', 'Computers');
INSERT INTO supplier (id, name, description) VALUES (3, 'Xiaomi', 'Phone manufacturer');
INSERT INTO supplier (id, name, description) VALUES (4, 'Toshiba', 'Japanese phone manufacturer');
INSERT INTO supplier (id, name, description) VALUES (5, 'Nerf', 'Gun manufacture');
INSERT INTO supplier (id, name, description) VALUES (6, 'DirTech', 'Best gardening equipment provider around the globe');

INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (1, 'Amazon Fire', 49, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (2, 'Lenovo IdeaPad Miix 700', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (3, 'Amazon Fire HD 8', 89, 'USD', 'Amazons latest Fire HD 8 tablet is a great value for media consumption.', 1, 1);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (4, 'Xiaomi 10', 100, 'USD', 'Best phone manufacturer in China, Supports Google.', 2, 3);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (5, 'Nerf Elite Titan CS-50', 420, 'USD', 'Take on targets with the power and size of a giant with Nerf Elite Titan CS-50 blaster!', 3, 5);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (6, 'Nerf N-Strike Rhino-Fire', 1500, 'USD', 'Win the battle with the most BRUTAL GUN!', 3, 5);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (7, 'Nerf Ultra Select', 145, 'USD', 'Play safe with your kid without hurt him.', 3, 5);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (8, 'Nerf Round', 10, 'USD', 'Best ammunition for battle.', 3, 5);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (9, 'Used Phone', 50, 'USD', 'Barely used.', 2, 3);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (10, 'Dirt', 2, 'USD', 'A handful of dirt.', 4, 6);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (11, 'Grass Block', 5, 'USD', 'Plantable grass.', 4, 6);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (12, 'Bagger 228', 1000, 'USD', 'Handy tool for excavation.', 4, 6);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (13, 'Shovel', 3, 'USD', 'For smaller gardening projects.', 4, 6);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (14, 'Leaf', 3, 'USD', 'A leaf.', 4, 6);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (15, 'Toshiba Handybook', 50, 'USD', 'Toshiba tablet for personal use.', 1, 4);
INSERT INTO product (id, name, price, currency, description, category_id, supplier_id) VALUES (16, 'Toshiba g450', 30, 'USD', 'A bit wacky design.', 2, 4);

INSERT INTO creditcard (id, user_id, card_number, card_holder, exp_year, exp_month, cvv) VALUES (1, 1, '123456789', 'Kis Pista', 21, 12, 123);
INSERT INTO creditcard (id, user_id, card_number, card_holder, exp_year, exp_month, cvv) VALUES (2, 2, '987654321', 'Nagy Geza', 23, 12, 321);

INSERT INTO paypal (user_id, username, password) VALUES (1, 'kispista', 'kp123');
INSERT INTO paypal (user_id, username, password) VALUES (1, 'nagygeza', 'ng321');
