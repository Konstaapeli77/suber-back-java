DELETE FROM person;
DELETE FROM service;
DELETE FROM company;
DELETE FROM address;

INSERT INTO address (id, address, postal_code, city)
Values (1, 'Horsmakuja 1a A 5', '01300', 'Vantaa');

INSERT INTO address (id, address, postal_code, city)
Values (2, 'Liusketie 3 A 1', '01120', 'Helsinki');

INSERT INTO address (id, address, postal_code, city)
Values (3, 'Mannerheimintie 10 B 2', '00010', 'Helsinki');

INSERT INTO company (id, name, business_id, address_id)
VALUES (1, 'Tikkurila Oy', '1412331-1', 1);

INSERT INTO company (id, name, business_id, address_id)
VALUES (2, 'Kekkilä Oy', '412345-1', 2);

INSERT INTO service (id, description, name, price, company_id)
VALUES (1, 'Lumityöt lapiolla.', 'Lumityöt lapiolla per tunti', 20.00, 1);

INSERT INTO service (id, description, name, price, company_id)
VALUES (2, 'Lumityöt kolalla.', 'Lumityöt kolalla per tunti', 10.00, 1);

INSERT INTO person (id, firstname, lastname, address_id)
VALUES (1, 'Matti', 'Vanhanen', 2);

INSERT INTO person (id, firstname, lastname, address_id)
VALUES (2, 'Vilma', 'Vanhanen', 2);

INSERT INTO person (id, firstname, lastname, address_id)
VALUES (3, 'Ville', 'Koivu', 3);

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

