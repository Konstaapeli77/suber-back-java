DELETE FROM person;
DELETE FROM service;
DELETE FROM company;
DELETE FROM address;

INSERT INTO address (address, postal_code, city)
Values ('Horsmakuja 1a A 5', '01300', 'Vantaa');

INSERT INTO address (address, postal_code, city)
Values ('Liusketie 3 A 1', '01120', 'Helsinki');

INSERT INTO address (address, postal_code, city)
Values ('Mannerheimintie 10 B 2', '00010', 'Helsinki');

INSERT INTO company (name, business_id, address_id)
VALUES ('Tikkurila Oy', '1412331-1', 1);

INSERT INTO company (name, business_id, address_id)
VALUES ('Kekkilä Oy', '412345-1', 2);

INSERT INTO service (description, name, price, company_id)
VALUES ('Lumityöt lapiolla.', 'Lumityöt lapiolla per tunti', 20.00, 1);

INSERT INTO service (description, name, price, company_id)
VALUES ('Lumityöt kolalla.', 'Lumityöt kolalla per tunti', 10.00, 1);

INSERT INTO person (firstname, lastname, address_id)
VALUES ('Matti', 'Vanhanen', 2);

INSERT INTO person (firstname, lastname, address_id)
VALUES ('Vilma', 'Vanhanen', 2);

INSERT INTO person (firstname, lastname, address_id)
VALUES ('Ville', 'Koivu', 3);

INSERT INTO orders (id, price, reference, address_id, customer_id, person_id)
VALUES (1, 120, '25521', 1, 1, 1);

INSERT INTO orders (id, price, reference, address_id, customer_id, person_id)
VALUES (2, 210, '123', 1, 1, 1);

INSERT INTO services_orders (service_id, order_id)
VALUES (1, 1);

INSERT INTO services_orders (service_id, order_id)
VALUES (2, 1);

INSERT INTO services_orders (service_id, order_id)
VALUES (2, 2);

