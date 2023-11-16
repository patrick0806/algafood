INSERT INTO states (name) VALUES ('Minas Gerais');
INSERT INTO states (name) VALUES ('São Paulo');
INSERT INTO states (name) VALUES ('Ceará');

INSERT INTO cities (name, state_id) VALUES ("UBERLANDIA", 1);
INSERT INTO cities (name, state_id) VALUES ("Belo Horizonte", 1);
INSERT INTO cities (name, state_id) VALUES ("São Paulo", 2);
INSERT INTO cities (name, state_id) VALUES ("Campinas", 2);
INSERT INTO cities (name, state_id) VALUES ("Fortaleza", 3);


INSERT INTO gastronomy_styles (name) VALUES ('Tailandesa');
INSERT INTO gastronomy_styles (name) VALUES ('Indiana');
INSERT INTO gastronomy_styles (name) VALUES ('Argentina');
INSERT INTO gastronomy_styles (name) VALUES ('Brasileira');

INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
    VALUES ( 'Thai Gourmet', 10, 1, NOW(), NOW(), '13875-286', 'Sebastião Camargo', '69', NULL, 'Jardim Crepusculo', 1);
INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
     VALUES ( 'Thai Delivery', 9.50, 1, NOW(), NOW(), '13875-286', 'Sebastião Camargo', '70', NULL, 'Jardim Crepusculo', 1);
INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
     VALUES ( 'Tuk Tuk Comida indiana', 15, 2, NOW(), NOW(), '13875-286', 'Alfeu Fiorim', '71', NULL, 'Jardim Crepusculo', 1);
INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
     VALUES ( 'Java Steakhouse', 12, 3, NOW(), NOW(), '13875-286', 'Alfeu Fiorim', '72', NULL, 'Jardim Crepusculo', 1);
INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
     VALUES ('Lanchonete do Tio Sam', 11, 4, NOW(), NOW(), '13875-286', 'Alfeu Fiorim', '72', NULL, 'Jardim Crepusculo', 1);
INSERT INTO restaurants (name, delivery_tax, gastronomy_style_id, creation_date, update_date, zip_code, street, number, complement, neighborhood, city_id)
     VALUES ('Bar da Maria', 4, 4, NOW(), NOW(), '13875-286', 'Alfeu Fiorim', '72', NULL, 'Jardim Crepusculo', 1);

INSERT INTO payment_methods (description) VALUES ('Cartão de crédito');
INSERT INTO payment_methods (description) VALUES ('Cartão de Débito');
INSERT INTO payment_methods (description) VALUES ('Dinheiro');

INSERT INTO permissions (name, description) VALUES  ('CONSULTAR_COZINHAS', 'Permite consultar culinarias');
INSERT INTO permissions (name, description) VALUES  ('EDITAR_COZINHAS', 'Permite editar culinarias');

INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (1,1);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (1,2);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (1,3);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (2,3);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (3,2);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (3,3);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (4,1);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (5,2);
INSERT INTO restaurant_payment_methods (restaurant_id, payment_method_id) VALUES (6,3);

INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('Porco com Molho Agridoce', 'Deliciona carne suina ao molho especial', 10.00, true, 1);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('Camarão Tailandês', '16 Camarões ao moho picante', 20.00, true, 1);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES('Salada picante com carne grelhada', 'Descrição do Produto 3', 30.00, true, 1);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('Garlic Nann', 'Pão tradiciona Indiano com corbertura de alho', 79.90, true, 2);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('Murg Curry', 'Cubos de frango, preparados com molho curry', 20.00, true, 3);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES('Bife Ancho', 'Corte maciol e suculento, com dois dedos de espessura', 30.00, true, 4);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('T-Bone', 'Corte Saboroso', 10.00, true, 5);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES ('X-Tudo', 'Sandubão com muito queijo', 20.00, true, 6);
INSERT INTO products (name, description, price, active, restaurant_id) VALUES('Espetinho de Cupim', 'Acompanha Farinha, mandioca e vinagrete', 30.00, true, 6);