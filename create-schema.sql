PRAGMA foreign_keys = ON;
DROP TABLE IF EXISTS recipes;
CREATE TABLE recipes(
	rec_name	TEXT,
	PRIMARY KEY (rec_name)
	);
	
DROP TABLE IF EXISTS ingredients;
CREATE TABLE ingredients	(
	ing_name	TEXT,
	unit	 	TEXT,
	amount	 	DOUBLE(12,2),
	date_of_deliv	DATE,
	size_of_deliv	DOUBLE(12, 2),
	PRIMARY KEY (ing_name)
	);
	
DROP TABLE IF EXISTS rec_ing;
CREATE TABLE rec_ing(
	rec_name	TEXT,
	ing_name	TEXT,
	amount		DOUBLE(12,2),
	PRIMARY KEY (rec_name, ing_name),
	FOREIGN KEY (rec_name) REFERENCES recipes(rec_name),
	FOREIGN KEY (ing_name) REFERENCES ingredients(ing_name)
	);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers(
	c_name	TEXT,
	address	TEXT,
	phn_nbr	TEXT,
	PRIMARY KEY (c_name)
	);
	
	
DROP TABLE IF EXISTS orders;
CREATE TABLE orders(
	order_id		INTEGER,
	delivered_by	DATE,
	c_name	TEXT,
	PRIMARY KEY (order_id),
	FOREIGN KEY (c_name) REFERENCES customers(c_name)
	);
DROP TABLE IF EXISTS pallets;
CREATE TABLE pallets(
	pallet_id	INTEGER,
	status		INTEGER DEFAULT 0 CHECK(status <= 3 AND status >= 0),
	prod_date	DATE,
	rec_name	TEXT,
	order_id	INTEGER,
	PRIMARY KEY (pallet_id),
	FOREIGN KEY (rec_name) REFERENCES recipes(rec_name),
	FOREIGN KEY	(order_id) REFERENCES orders(order_id)
	);
	
DROP TABLE IF EXISTS deliveries;
CREATE TABLE deliveries(
	delivery_id	INTEGER,
	delivery_date	DATE,
	PRIMARY KEY (delivery_id)
	);

DROP TABLE IF EXISTS delivery_info;
CREATE TABLE delivery_info(
	delivery_id	INTEGER,
	order_id	INTEGER,
	amount_delivered	INTEGER,
	PRIMARY KEY (delivery_id, order_id),
	FOREIGN KEY (delivery_id) REFERENCES deliveries(delivery_id),
	FOREIGN KEY (order_id) REFERENCES orders(order_id)
	);
	
DROP TABLE IF EXISTS blocked;
CREATE TABLE blocked(
	rec_name	TEXT,
	start_date	DATE,
	end_date	DATE,
	PRIMARY KEY (rec_name, start_date),
	FOREIGN KEY (rec_name) REFERENCES recipes(rec_name)
	);

DROP TABLE IF EXISTS order_info;
CREATE TABLE order_info(
	rec_name	TEXT,
	order_id	INTEGER,
	no_pallets	INTEGER,
	PRIMARY KEY(rec_name, order_id),
	FOREIGN KEY (rec_name) REFERENCES recipes(rec_name),
	FOREIGN KEY (order_id) REFERENCES orders(order_id)
	);
PRAGMA foreign_keys = ON;




	
	