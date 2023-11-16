CREATE TABLE orders(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   subtotal BIGINT NOT NULL,
   delivery_tax BIGINT NOT NULL,
   total_price BIGINT NOT NULL,
   zip_code VARCHAR(255) NOT NULL,
   street VARCHAR(255) NOT NULL,
   number VARCHAR(255) NOT NULL,
   complement VARCHAR(255),
   neighborhood VARCHAR(255) NOT NULL,
   city_id BIGINT,
   creation_date DATETIME NOT NULL,
   confirmation_date DATETIME,
   payment_method_id BIGINT NOT NULL,
   client_id BIGINT NOT NULL,

   FOREIGN KEY (client_id) REFERENCES users(id),
   FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id),
   FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE TABLE order_items(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   unity_price BIGINT NOT NULL,
   amount INT NOT NULL,
   total_price BIGINT NOT NULL,
   order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,

   FOREIGN KEY (order_id) REFERENCES orders(id),
   FOREIGN KEY (product_id) REFERENCES products(id)
);