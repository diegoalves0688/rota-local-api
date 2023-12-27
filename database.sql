
/*----------------------------------------------------------------
                    POSTGRESQL | ROTALOCAL 
----------------------------------------------------------------*/

------------------------- EXCLUIR TABELAS -------------------------

DROP TABLE IF EXISTS recomendacao_atracao CASCADE;
DROP TABLE IF EXISTS avaliacao_atracao CASCADE;
DROP TABLE IF EXISTS avaliacao_recomendacao CASCADE;
DROP TABLE IF EXISTS atracao CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS localizacao CASCADE;
DROP TABLE IF EXISTS imagem CASCADE;
DROP TABLE IF EXISTS recomendacao CASCADE;
DROP TYPE IF EXISTS perfil_usuario CASCADE;
DROP TYPE IF EXISTS categoria_atracao CASCADE;
DROP TYPE IF EXISTS status_atracao CASCADE;


------------------------- CRIAR TABELAS -------------------------
--CLASSES
CREATE TABLE usuario(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   senha VARCHAR(255) NOT NULL,
   foto VARCHAR(255) NOT NULL,
   ativo boolean NOT NULL,
   perfil VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE localizacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   pais VARCHAR(255) NOT NULL,
   estado VARCHAR(255) NOT NULL,
   cidade VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   descricao text NOT NULL,
   ativo boolean NOT NULL,
   categoria VARCHAR(255) NOT NULL, 
   status VARCHAR(255) NOT NULL, 
   data_registro timestamp not null,
   usuario_id BIGINT,
   localizacao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_localizacao FOREIGN KEY(localizacao_id) REFERENCES localizacao(id)

);

CREATE TABLE imagem(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(255) NOT NULL,
   url_caminho VARCHAR(255) NOT NULL,
   atracao_id BIGINT,
   usuario_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

CREATE TABLE avaliacao_atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id BIGINT,
   atracao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE recomendacao_atracao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   recomendacao text NOT NULL,
   data_registro timestamp not null,
   usuario_id BIGINT,
   atracao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_atracao FOREIGN KEY(atracao_id) REFERENCES atracao(id)
);

CREATE TABLE avaliacao_recomendacao(
   id BIGINT GENERATED ALWAYS AS IDENTITY,
   avaliacao_positiva BOOLEAN NOT NULL,
   usuario_id BIGINT,
   recomendacao_id BIGINT,
   PRIMARY KEY(id),
   CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id),
   CONSTRAINT fk_recomendacao FOREIGN KEY(recomendacao_id) REFERENCES recomendacao_atracao(id)
);


------------------------- POPULAR TABELAS -------------------------
	
INSERT INTO public.localizacao (pais,estado,cidade) VALUES
	 ('Brasil','Rio de Janeiro','Macae')
   ,('Brasil', 'São Paulo', 'São Paulo')
   ,('Brasil', 'Rio de Janeiro', 'Rio de Janeiro')
   ,('Brasil', 'Minas Gerais', 'Belo Horizonte')
   ,('Brasil', 'São Paulo', 'São Paulo')
   ,('Brasil', 'Rio de Janeiro', 'Rio de Janeiro')
   ,('Brasil', 'Minas Gerais', 'Belo Horizonte')
   ,('Brasil', 'Bahia', 'Salvador')
   ,('Brasil', 'Ceará', 'Fortaleza')
   ,('Brasil', 'Paraná', 'Curitiba')
   ,('Brasil', 'Rio Grande do Sul', 'Porto Alegre')
   ,('Brasil', 'Pernambuco', 'Recife')
   ,('Brasil', 'Amazonas', 'Manaus')
   ,('Brasil', 'Distrito Federal', 'Brasília');

INSERT INTO public.usuario (nome, email, senha, foto, ativo, perfil)
VALUES ('John', 'john@mail.com', 'senha-123', 'foto-john', true, 'ADMINISTRADOR');

INSERT INTO public.usuario (nome, email, senha, foto, ativo, perfil)
VALUES ('lud', 'lud@mail.com', 'senha-123', 'foto-lud', true, 'COLABORADOR');

INSERT INTO public.usuario (nome, email, senha, foto, ativo, perfil)
VALUES ('mary', 'mary@mail.com', 'senha-123', 'foto-mary', true, 'COLABORADOR');

INSERT INTO public.atracao (nome,descricao,ativo,categoria, status, data_registro, usuario_id, localizacao_id) VALUES
('Praia das pedrinhas','descricao pedrinhas', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,1),
('Praia das conchas','descricao conchas', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,1),
('cristo rendentor','estatua grande e longe', true,'MONUMENTOS' ,'PUBLICO', current_timestamp,2,3);

INSERT INTO public.avaliacao_atracao (avaliacao_positiva,usuario_id,atracao_id) VALUES
(true,1,1);
	
INSERT INTO public.recomendacao_atracao (recomendacao, data_registro, usuario_id, atracao_id) VALUES
('recomendacao teste 01 ', current_timestamp, 1,1);

INSERT INTO public.avaliacao_recomendacao (avaliacao_positiva,usuario_id,recomendacao_id) VALUES
(true,1,1);

INSERT INTO public.imagem (nome,url_caminho,usuario_id,atracao_id) VALUES
('banner.png','http://localhost:8080/images/banner.png',1,1),
('banner.png','http://localhost:8080/images/banner.png',1,2),
('banner.png','http://localhost:8080/images/banner.png',1,3);
	 
	 

------------------------- VISUALIZAR TABELAS -------------------------
SELECT * FROM ATRACAO;
SELECT * FROM AVALIACAO_ATRACAO;
SELECT * FROM AVALIACAO_RECOMENDACAO;
SELECT * FROM IMAGEM;
SELECT * FROM LOCALIZACAO;
SELECT * FROM RECOMENDACAO_ATRACAO;
SELECT * FROM USUARIO;
/*
-- ESTADO INICIAL DO BD 
SELECT * FROM USUARIO;
--3 usuarios john 1 adm , lud 2 colab, mary colab

SELECT * FROM ATRACAO; 
--2 atracaoes da mesma localizacao 1 e de usuario 1 - sao 2 PRAIIAS
--1 atracaoes da       localizacao 3 e de usuario 2 - é MONUMENTOS

SELECT * FROM AVALIACAO_ATRACAO;
-- 1 avalicao do usuario 1 na atracao 1 como positiva

SELECT * FROM RECOMENDACAO_ATRACAO;
-- 1 recomendacao pra atracao 1 do usuario 1

SELECT * FROM AVALIACAO_RECOMENDACAO;
-- 1 avalicao do usuario 1 na atracao 1 como positiva

SELECT * FROM IMAGEM;
--2 imagens 1 pra cada atracao (1 e 2) - todas do usuario 1

SELECT * FROM LOCALIZACAO;
--14 localizacoes
*/