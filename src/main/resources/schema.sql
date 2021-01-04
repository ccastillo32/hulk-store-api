DROP TABLE IF EXISTS movements;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS franchises;

CREATE TABLE categories (
	
	id CHAR(36) NOT NULL,
	name VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)
	
);

CREATE TABLE franchises (
	
	id CHAR(36) NOT NULL,
	name VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)
	
);

CREATE TABLE products (

	id CHAR(36) NOT NULL,
	code VARCHAR(50) NOT NULL,
	name VARCHAR(150) NOT NULL,
	purchase_price DECIMAL(10,2) NOT NULL,
	selling_price DECIMAL(10,2) NOT NULL,
	category_id CHAR(36) NOT NULL,
	franchise_id CHAR(36) NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (category_id) REFERENCES categories (id),
	FOREIGN KEY (franchise_id) REFERENCES franchises (id)
	
);

CREATE TABLE movements (

	id CHAR(36) NOT NULL,
	product_id CHAR(36) NOT NULL,
	type VARCHAR(20) NOT NULL,
	quantity INTEGER NOT NULL,
	price DECIMAL (10,2) NOT NULL,
	observation VARCHAR(250),
	created_at DATETIME NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (product_id) REFERENCES products (id)

);