# rota-local-api
Backend project

## How to contribute

1º Clone o projeto selecionando o endereço no repositório

```git clone git@github.com:diegoalves0688/rota-local-api.git```

2º Crie uma branch para sua nova feature usando o codigo abaixo

```git checkout -b nome-da-minha-branch```

3º Altere o código como desejar

4ª Adicionar as alterações para o staging. Na raiz do repositório ou na IDE:

```git add .```

5º Criar o commit do que foi adicionado ao staging

```git commit -m "exemplo de comentario"```

6º Faça o Push do seu commit para o Github

```git push```

ou 

```git push --set-upstream origin nome-da-minha-branch```

## Como autenticar na api

1° Enviar uma requisição com o usuário e senha para a api:

```
curl --location 'http://localhost:8080/api/usuario/autenticar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "mail@mail.com",
    "senha": "123"
}'
```

2° Utilizar o `userId` e o `token` retornados na resposta para autenticar as requisições, passando os valores como headers `X-API-KEY` e `X-API-TOKEN`, como por exemplo:
 
```
curl --location 'localhost:8080/api/atracao' \
--header 'X-API-KEY: aqui-vai-o-userId' \
--header 'X-API-TOKEN: aqui-vai-o-token' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "batcaverna-testehhhNZZZ",
    "descricao": "cafofo do batmanhhhhN",
    "categoria": "TRILHAS",
    "usuario": {
        "id": 2
    },
    "localizacao": {
        "id": 4
    }
}'
```

## Collection do Postman

[link](https://github.com/diegoalves0688/rota-local-api/blob/main/rota_local_api.postman_collection.json)

## SQL com o banco de dados

[link](https://github.com/diegoalves0688/rota-local-api/blob/main/database.sql)
