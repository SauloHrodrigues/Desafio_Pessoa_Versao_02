# API Desafio Pessoa Versão 2

A API foi desenvolvida para gerenciar o cadastro de Pessoas e seus repectivos endereços. 
Seu contexto de desenvolvimento é o exercício e desafio
de boas práticas de programação orientada a objeto, bem como a aplicação das arquiteturas de mercado.
Boas práticas utilizando padrões de projeto, princípios SOLID e conceitos de Clean Code, além do desenvolvimento de testes unitários e automatizados utilizando JUnit e Mockito.

---

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-FF6C37?style=for-the-badge&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-DB7093?style=for-the-badge&logo=mockito&logoColor=white)

---
## Do Banco de Dados

O projeto faz uso do **H2 banco de dados em memória**, não necessitando de
qualquer configuraçã:

**Obs.** A definição do banco foi determinada no escopo do projeto;

---

## Estrutura do projeto

O projeto tem duas entidades. Pessoa e Endereço, com a seguite estrutura:

````
{
  "nome": "Paulo Silva",
  "dataDeNascimento": "1954-07-29",
  "cpf": "123.456.789-09",
  "enderecos": [
    {
      "rua": "Rua das Flores",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01000-000",
      "enderecoPrincipal": true
    }
  ````
Salienta-se que uma pessoa tem uma lista de endereços, sendo que, apenas um desses, pode ser o principal.

As ações possibilitadas pelo CUD são:

#### * Criar uma nova pessoa:

Para criar uma nova pessoa deve-se acessar o seguinte **endpoint**:

````
 POST - http://localhost:8080/pessoashttp://localhost:8080/pessoas
```` 

Enviando no body da requisição um objeto com a seguinte estrutura:

````
{
  "nome": "Maria Antonieta da Silva",
  "dataDeNascimento": "1978-09-12",
  "cpf": "123.456.789-09",
  "enderecos": [
    {
      "rua": "Av. Brasil",
      "numero": "100",
      "bairro": "copacabana",
      "cidade": "Rio de Janeiro",
      "estado": "RJ",
      "cep": "20000-000",
      "enderecoPrincipal": true 
    }
  ]
}
````

Ressaltasse, no objeto acima, que todos os atributos são obrigatórios 
e endereço trata-se de uma lista, não podendo ser vazia; 

O **endpoit** ora em comento tem como resposta um status code 201 created acrecentado do 
seguinte objeto:

````
    {
  "id": 0,
  "nome": "string",
  "dataDeNascimento": "2026-01-07",
  "cpf": "string",
  "enderecos": [
    {
      "id": 0,
      "rua": "string",
      "numero": "string",
      "bairro": "string",
      "cidade": "string",
      "estado": "string",
      "cep": "string",
      "enderecoPrincipal": true
    }
  ]
````

#### Listar pessoas do banco

Para obter todas as pessoas cadastradas no banco, deve-se utilizar
o seguinte **endpoint**

````
 GET - http://localhost:8080/pessoashttp://localhost:8080/pessoas
```` 
Esse endpoint dispensa o envio de qualquer objeto ou atributo
em seu corpo, retornando ao cliente o seguinte objeto:

````
**{
  "totalElements": 0,
  "totalPages": 0,
  "first": true,
  "last": true,
  "size": 0,
  "content": [
    {
      "id": 0,
      "nome": "string",
      "dataDeNascimento": "2026-01-07",
      "cpf": "string",
      "enderecos": [
        {
          "id": 0,
          "rua": "string",
          "numero": "string",
          "bairro": "string",
          "cidade": "string",
          "estado": "string",
          "cep": "string",
          "enderecoPrincipal": true
        }
      ]
    }
  ],
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "pageNumber": 0,
    "pageSize": 0,
    "paged": true,
    "unpaged": true
  },
  "empty": true
}**
````

#### Retornar idade da pessoa

O **endpoint** para retormar a idade da pessoa é o segunte:

````
 GET - http://localhost:8080/pessoas/id/mostrar_idade
````

Esse endpoint dipensa envio de objeto no body, nessessitando
apenas o envio do id (**identificador da pessoa no banco**), na **URL**.

A resposta desse endpoint é o seguinte objeto, com **status code 200**:

````
{
  "id": 0,
  "nome": "string",
  "cpf": "string",
  "dataDeNascimento": "2026-01-07",
  "idade": 0
}

````

#### Atualizar uma pessoa

Para **Atualizar** uma pessoa deve-se acessar o seguinte **endpoint**:

````
 PACTH - http://localhost:8080/pessoas/id
```` 

Enviando o id da pessoa na **URL**, e no body da requisição um objeto com a seguinte estrutura:

````
{
  "nome": "string",
  "dataDeNascimento": "2026-01-07",
  "cpf": "string",
  "enderecos": [
    {
      "id": 0,
      "rua": "string",
      "numero": "string",
      "bairro": "string",
      "cidade": "string",
      "estado": "string",
      "cep": "string",
      "endrecoPrincipal": true
    }
  ]
}
````
Importante ressaltar, que todos os atributos, para atualização, 
são opcionais, sendo alterados apenas os enviados.

**Observação importante**:

````
Caso queira alterar um dos endereços, o id do mesmo deve estar no
copo da requisição. Caso contrário, o endereço não será atualizado.
````
#### deletar

Para apagar uma pessoa do banco deve-se utilizar o seguinte endpoint:

````
 DELETE - http://localhost:8080/pessoas/id
````
Esse endpoint não retorna qualquer objeto. Apenas status code 204.

---

## Execução 

O projeto poderá ser executado pela ide dando o play ou pelo terminal, com o seguinte comamdo:

```bash
mvn spring-boot:run
```
---

## Testes

O projeto conta com testes unitários e integrados;

* Executando os testes unitários:

Na raiz do projeto execute: 
```bash     mvn test```

* Executando os testes integrados:

Na raiz do projeto execute: 
```bash     mvn clean install```

## Considerações finais


##### Projeto no GitHub: https://github.com/SauloHrodrigues/Desafio_Votacao_V2.git

## Autor:

### Nome: Saulo Henrique Rodrigues

##### LinkedIn: https://www.linkedin.com/in/saulohenriquerodrigues/

##### Swagger: http://localhost:8080/swagger-ui.html

