
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
	('Brasil','Rio de Janeiro','Macaé')
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

INSERT INTO public.usuario (nome, email, senha, foto, ativo, perfil) VALUES 
('monica',    'monica@mail.com'   , 'senha-123', 'http://localhost:8080/images/monica.jpg'   , true, 'ADMINISTRADOR'),
('cebolinha', 'cebolinha@mail.com', 'senha-123', 'http://localhost:8080/images/cebolinha.png', true, 'ADMINISTRADOR'),
('batman',    'batman@mail.com'   , 'senha-123', 'http://localhost:8080/images/batman.jpg'   , true, 'COLABORADOR'),
('coringa',   'coringa@mail.com'  , 'senha-123', 'http://localhost:8080/images/coringa.png'  , true, 'COLABORADOR'), --4
('wolverine', 'wolverine@mail.com', 'senha-123', 'http://localhost:8080/images/wolverine.jpg', true, 'COLABORADOR'),
('jean grey', 'jeangrey@mail.com' , 'senha-123', 'http://localhost:8080/images/jean-grey.png', true, 'COLABORADOR'),
('ciclope',   'ciclope@mail.com'  , 'senha-123', 'http://localhost:8080/images/ciclope.png'  , true, 'COLABORADOR'), --7
('arlequina', 'arlequina@mail.com', 'senha-123', 'http://localhost:8080/images/arlequina.png', true, 'COLABORADOR'),
('pinguim',   'pinguim@mail.com'  , 'senha-123', 'http://localhost:8080/images/pinguim.png'  , true, 'COLABORADOR');

INSERT INTO public.atracao (nome, descricao, ativo, categoria, status, data_registro, usuario_id, localizacao_id) VALUES
('Praia das pedrinhas','descricao pedrinhas', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,1),
('Praia das ilha das couves','praia tranquila e segura', true,'PRAIAS' ,'PUBLICO', current_timestamp,1,2),
('cristo rendentor','estatua grande e longe', true,'MONUMENTOS' ,'PUBLICO', current_timestamp,2,3),
('batcaverna','cafofo do batman', true, 'MONUMENTOS' ,'PUBLICO', current_timestamp,3,5),
('fogo de chao','hora do churrasco', true, 'RESTAURANTES' ,'PUBLICO', current_timestamp,5,7),
('masp','museu muito bom ', true, 'MUSEUS' ,'PUBLICO', current_timestamp,4,2),
('cachoeira do buracao','sensacional, lugar lindo ', true, 'CACHOEIRAS' ,'PUBLICO', current_timestamp,6,8),
('pinguim nightclub','show de bola para dançar, lotado no fds', true, 'BOATES' ,'PUBLICO', current_timestamp,9,12),
('trilha do rio do boi', 'trilha maravilhosa!!!', true, 'TRILHAS' ,'PUBLICO', current_timestamp,7,11);


INSERT INTO public.imagem (nome,url_caminho,usuario_id,atracao_id) VALUES
('praia-pedrinhas.jpg','http://localhost:8080/images/praia-pedrinhas.jpg',1,1),
('praia-ilha-couves.jpg','http://localhost:8080/images/praia-ilha-couves.jpg',1,2),
('cristo-redentor.jpg','http://localhost:8080/images/cristo-redentor.jpg',2,3),
('batcaverna.png','http://localhost:8080/images/batcaverna.png',3,4),
('fogo-chao.jpg','http://localhost:8080/images/fogo-chao.jpg',5,5),
('masp.jpg','http://localhost:8080/images/masp.jpg',4,6),
('cachoeira-buracao.jpg','http://localhost:8080/images/cachoeira-buracao.jpg',6,7),
('boate.jpg','http://localhost:8080/images/boate.jpg',9,8),
('trilha-rio-boi.jpeg','http://localhost:8080/images/trilha-rio-boi.jpeg',7,9);


INSERT INTO public.avaliacao_atracao (avaliacao_positiva,usuario_id,atracao_id) VALUES
(true,1,1),
(true,2,1),
(true,3,1),
(true,4,1),
(true,5,1),
(false,1,2),
(false,2,2),
(false,3,2),
(false,4,2),
(false,5,2),
(false,6,2),
(true,4,3),
(true,5,3),
(false,1,3),
(true,1,4),
(true,1,4),
(false,2,4),
(true,4,4),
(true,5,4),
(true,6,4),
(true,1,5),
(true,6,5),
(true,3,6),
(true,8,6),
(true,9,6),
(false,1,6),
(true,6,7),
(true,8,7),
(true,3,7),
(true,5,8),
(false,1,8),
(false,2,8),
(false,3,8),
(false,4,8),
(false,7,8),
(false,5,9);
	
INSERT INTO public.recomendacao_atracao (recomendacao, data_registro, usuario_id, atracao_id) VALUES
('consegui pegar um bronze', current_timestamp, 2,1),
('nao é tranquila! roubaram minha bolsa', current_timestamp, 6,2),
('muito quente, mas sensacional', current_timestamp, 9,3),
('horrivel esse lugar, cheio de morcego', current_timestamp, 9,4),
('fiquei sabendo que o wolverine usa as garrinhas pra cortar as carnes', current_timestamp, 7,5),
('único lugar que nao brigo com o coringa!', current_timestamp, 3,6),
('vou direto com o pinguim nadar ai', current_timestamp, 4,7),
('lugar LIXO', current_timestamp, 3,8),
('romantico, paisagem linda :) ', current_timestamp, 6,9);

INSERT INTO public.avaliacao_recomendacao (avaliacao_positiva,usuario_id,recomendacao_id) VALUES
(true,3,1),
(true,2,1),
(true,4,1),
(true,5,1),
(true,1,2),
(true,3,2),
(true,1,3),
(true,2,3),
(true,4,3),
(true,5,3),
(true,6,3),
(true,7,3),
(false,8,3),
(true,8,4),
(true,4,4),
(true,2,5),
(true,3,5),
(true,7,5),
(false,1,5),
(true,8,6),
(true,6,7),
(true,8,7),
(true,3,7),
(true,1,8),
(true,2,8),
(true,5,8),
(false,9,8),
(false,4,8),
(false,8,8),
(false,8,8),
(false,5,9);
 