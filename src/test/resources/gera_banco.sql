
INSERT INTO pessoas (id, nome, data_de_nascimento, cpf) VALUES
                                                            (1, 'João da Silva', '1990-05-10', '11111111111'),
                                                            (2, 'Maria Oliveira', '1985-08-22', '22222222222'),
                                                            (3, 'Carlos Santos', '2000-01-15', '33333333333');

INSERT INTO enderecos
(rua, numero, bairro, cidade, estado, cep, endereco_principal, pessoa_id)
VALUES
    ('Rua das Flores', '100', 'Centro', 'São Paulo', 'SP', '01000000', true, 1),
    ('Av. Paulista', '1500', 'Bela Vista', 'São Paulo', 'SP', '01310000', false, 1),
    ('Rua Augusta', '300', 'Consolação', 'São Paulo', 'SP', '01305000', false, 1);

INSERT INTO enderecos
(rua, numero, bairro, cidade, estado, cep, endereco_principal, pessoa_id)
VALUES
    ('Rua A', '50', 'Centro', 'Rio de Janeiro', 'RJ', '20000000', true, 2),
    ('Rua B', '120', 'Copacabana', 'Rio de Janeiro', 'RJ', '22000000', false, 2),
    ('Rua C', '900', 'Botafogo', 'Rio de Janeiro', 'RJ', '22250000', false, 2);

INSERT INTO enderecos
(rua, numero, bairro, cidade, estado, cep, endereco_principal, pessoa_id)
VALUES
    ('Rua Alpha', '10', 'Centro', 'Belo Horizonte', 'MG', '30000000', true, 3),
    ('Rua Beta', '200', 'Savassi', 'Belo Horizonte', 'MG', '30140000', false, 3),
    ('Rua Gama', '999', 'Pampulha', 'Belo Horizonte', 'MG', '31270000', false, 3);
