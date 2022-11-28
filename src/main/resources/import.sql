INSERT INTO state (name) VALUES ('Paraíba');

INSERT INTO city (name, state_id) VALUES ('João Pessoa', 1);
INSERT INTO city (name, state_id) VALUES ('Campina Grande', 1);

INSERT INTO gastronomy (name) VALUES ('Tailandesa');
INSERT INTO gastronomy (name) VALUES ('Chinesa');
INSERT INTO gastronomy (name) VALUES ('Arabe');

INSERT INTO restaurant (name, rate, gastronomy_id) VALUES ('Thai Delivery', 20, 1);
INSERT INTO restaurant (name, rate, gastronomy_id) VALUES ('China Gourmet', 18, 2);
INSERT INTO restaurant (name, rate, gastronomy_id) VALUES ('Tur', 12, 3);


INSERT INTO payment (description) VALUES ('Cartão de Crédito');
INSERT INTO payment (description) VALUES ('Dinheiro');

INSERT INTO permission (name, description) VALUES ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (name, description) VALUES ('EDITAR_COZINHAS', 'Permite editar cozinhas');