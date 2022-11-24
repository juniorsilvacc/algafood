INSERT INTO cozinha (nome) VALUES ('Tailandesa');
INSERT INTO cozinha (nome) VALUES ('Chinesa');
INSERT INTO cozinha (nome) VALUES ('Arabe');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Delivery', 20, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('China Gourmet', 18, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tur', 12, 3);


INSERT INTO estado (nome) VALUES ('Paraíba');

INSERT INTO cidade (nome, estado_id) VALUES ('João Pessoa', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Campina Grande', 1);


INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro');

INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_COZINHAS', 'Permite editar cozinhas');