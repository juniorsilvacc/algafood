INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Chinesa');
INSERT INTO cozinha (id, nome) VALUES (3, 'Arabe');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (1, 'Thai Delivery', 20, 1);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (2, 'China Gourmet', 18, 2);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (3, 'Tur', 12, 3);


INSERT INTO estado (id, nome) VALUES (1, 'Paraíba');

INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'João Pessoa', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Campina Grande', 1);


INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');