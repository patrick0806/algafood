INSERT INTO gastronomy_styles (name) VALUES ('Tailandesa');
INSERT INTO gastronomy_styles (name) VALUES ('Indiana');

insert into restaurants (name, delivery_tax, gastronomy_style_id) values ('Thai Gourmet', 10, 1);
insert into restaurants (name, delivery_tax, gastronomy_style_id) values ('Thai Delivery', 9.50, 1);
insert into restaurants (name, delivery_tax, gastronomy_style_id) values ('Tuk Tuk Comida Indiana', 15, 2);

INSERT INTO states (name) VALUES ('Minas Gerais');
INSERT INTO states (name) VALUES ('São Paulo');
INSERT INTO states (name) VALUES ('Ceará');

INSERT INTO cities (name, state_id) VALUES ("UBERLANDIA", 1);
INSERT INTO cities (name, state_id) VALUES ("Belo Horizonte", 1);
INSERT INTO cities (name, state_id) VALUES ("São Paulo", 2);
INSERT INTO cities (name, state_id) VALUES ("Campinas", 2);
INSERT INTO cities (name, state_id) VALUES ("Fortaleza", 3);

INSERT INTO payment_methods (description) VALUES ('Cartão de crédito');
INSERT INTO payment_methods (description) VALUES ('Cartão de Débito');
INSERT INTO payment_methods (description) VALUES ('Dinheiro');

INSERT INTO permissions (name, description) VALUES  ('CONSULTAR_COZINHAS', 'Permite consultar culinarias');
INSERT INTO permissions (name, description) VALUES  ('EDITAR_COZINHAS', 'Permite editar culinarias');