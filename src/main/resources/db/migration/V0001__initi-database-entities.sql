CREATE TABLE states (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL
);

CREATE TABLE cities (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   state_id BIGINT,
   FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE TABLE `groups` (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL
);

CREATE TABLE permissions (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL
);

CREATE TABLE group_permissions (
   group_id BIGINT,
   permission_id BIGINT,
   PRIMARY KEY (group_id, permission_id),
   FOREIGN KEY (group_id) REFERENCES `groups`(id),
   FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE gastronomy_styles (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(60) NOT NULL
);


CREATE TABLE payment_methods (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   description VARCHAR(255) NOT NULL
);

CREATE TABLE restaurants (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  delivery_tax DECIMAL(10, 2) NOT NULL,
  gastronomy_style_id BIGINT,
  creation_date DATETIME NOT NULL,
  update_date DATETIME NOT NULL,
  zip_code VARCHAR(255) NOT NULL,
  street VARCHAR(255) NOT NULL,
  number VARCHAR(255) NOT NULL,
  complement VARCHAR(255),
  neighborhood VARCHAR(255) NOT NULL,
  city_id BIGINT,
  FOREIGN KEY (city_id) REFERENCES cities(id),
  FOREIGN KEY (gastronomy_style_id) REFERENCES gastronomy_styles(id)
);

CREATE TABLE products (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   description TEXT,
   price DECIMAL(10, 2) NOT NULL,
   active BOOLEAN NOT NULL,
   restaurant_id BIGINT,
   FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE restaurant_payment_methods (
   restaurant_id BIGINT,
   payment_method_id BIGINT,
   PRIMARY KEY (restaurant_id, payment_method_id),
   FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
   FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

CREATE TABLE users (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   creation_date DATETIME NOT NULL
);

CREATE TABLE user_groups (
   user_id BIGINT,
   group_id BIGINT,
   PRIMARY KEY (user_id, group_id),
   FOREIGN KEY (user_id) REFERENCES users(id),
   FOREIGN KEY (group_id) REFERENCES `groups`(id)
);
