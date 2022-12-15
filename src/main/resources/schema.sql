DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	id SERIAL NOT NULL,
	address varchar(255) NULL,
	city varchar(255) NULL,
	postal_code varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);


-- public.company definition

-- Drop table

-- DROP TABLE public.company;

CREATE TABLE public.company (
	id SERIAL NOT NULL,
	business_id varchar(255) NULL,
	"name" varchar(255) NULL,
	address_id int8 NULL,
	CONSTRAINT company_pkey PRIMARY KEY (id),
	CONSTRAINT fkgfifm4874ce6mecwj54wdb3ma FOREIGN KEY (address_id) REFERENCES public.address(id)
);


-- public.service definition

-- Drop table

-- DROP TABLE public.service;

CREATE TABLE public.service (
	id SERIAL NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	price numeric(19, 2) NULL,
	company_id int8 NULL,
	CONSTRAINT service_pkey PRIMARY KEY (id),
	CONSTRAINT fk3x1nsqno5u7l0rdu4aselnq7l FOREIGN KEY (company_id) REFERENCES public.company(id)
);


-- public.person definition

-- Drop table

-- DROP TABLE public.person;

CREATE TABLE public.person (
	id SERIAL NOT NULL,
	firstname varchar(255) NULL,
	lastname varchar(255) NULL,
	address_id int8 NULL,
	person_id int8 NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id),
	CONSTRAINT fkk7rgn6djxsv2j2bv1mvuxd4m9 FOREIGN KEY (address_id) REFERENCES public.address(id),
	CONSTRAINT fkqayg64etkrck83028q73kvo6y FOREIGN KEY (person_id) REFERENCES public.service(id)
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	id SERIAL NOT NULL,
	price numeric(19, 2) NULL,
	reference varchar(255) NULL,
	address_id int8 NULL,
	customer_id int8 NULL,
	company_id int8 NULL,
	person_id int8 NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id),
	CONSTRAINT fk8sxml705osl4yqpvai24htr3w FOREIGN KEY (customer_id) REFERENCES public.person(id),
	CONSTRAINT fkf5464gxwc32ongdvka2rtvw96 FOREIGN KEY (address_id) REFERENCES public.address(id),
	CONSTRAINT fkjp7lhaugpjb7u4su3h2khmnb3 FOREIGN KEY (company_id) REFERENCES public.company(id)
);


-- public.services_orders definition

-- Drop table

-- DROP TABLE public.services_orders;

CREATE TABLE public.services_orders (
	order_id int8 NOT NULL,
	service_id int8 NOT NULL,
	CONSTRAINT fkkvkfmlwsk71wqfci02eys9554 FOREIGN KEY (service_id) REFERENCES public.service(id),
	CONSTRAINT fkme7q4ecer98u6u1u19yv9ax8j FOREIGN KEY (order_id) REFERENCES public.orders(id)
);
