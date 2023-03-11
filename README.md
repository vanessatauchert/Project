# Desafio Taxa de Juros IBM

Utilizando boas práticas de desenvolvimento no software, crie uma aplicação para ler,
salvar e apresentar dados das taxas de juros de operações de crédito por instituicao 
financeira do catálogo de dados abertos do sistema financeiro nacional (DASFN) do
Banco Central do Brasil

- Fonte de dados:
https://dadosabertos.bcb.gov.br/dataset/taxas-de-juros-deoperacoes-de-credito

- Documentação API:
https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/documentacao

- Documentação Swagger:
https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/swagger-ui3#/

- Self-service API:
https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/aplicacao#!/recursos

## Requisitos

- ``Java 11``
- ``Intellij``
- ``Maven 3+``
- ``Mysql 8+``

## Desenvolvimento<br>
![Badge Finalizado](http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge)

## Passo 1:

Clonar o repositório<br>
Copie o link do repositório abaixo e clone para o diretório de sua preferência.

```
https://github.ibm.com/Van-Oliveira/ProjetoIBM-DASFN2.git
```

## Passo 2:

Configurar MySql<br>
Substitua no arquivo `src\main\resources\application.properties` os valores de usuario e senha, referentes ao seu banco.

```
spring.datasource.username=SeuUsuario
spring.datasource.password=Senha
```

## Passo 3:

Execute a aplicação para a criação da tabela `apijuros`

## APIS:

Após a execução do programa é possível visualizar os endpoints disponiveis no Swwager atráves do link: <http://localhost:8080/swagger-ui/index.html#/>

## Endpoints Onboard:

| Método | Url | Descrição |
| --- | --- | --- |
| GET | /api/externa/v1/listar | Lista todos os dados da API externa |
| POST | /api/externa/v1/salvar | Salva no banco todos os dados da API externa |


## Endpoints banco de dados:

| Método | Url | Descrição |
| --- | --- | --- |
| GET | /api/banco/v1/listar | Lista todos os registros do banco |
| GET | /api/banco/v1/listarPg | Lista todos os registros do banco paginado |
| GET | /api/banco/v1/id/{id} | Busca registro por Id |
| GET | /api/banco/v1/anoMes/{anoMes} | Filtra com base no anoMes (yyyy-dd) |
| GET | /api/banco/v1/anoMesPg/{anoMes} | Filtra com base no anoMes (yyyy-dd) paginado |
| POST | /api/banco/v1/criar | Cria um novo registro |
| PUT | /api/banco/v1/update/{id} | Atualiza um registro pelo id |
| DELETE | /api/banco/v1/delete/{id} | Deleta um registro pelo id |

## Collection Insomnia:

A collection dos endpoints no `Insomnia` pode ser encontrada para download na pasta: `src\main\resources\Insomnia_2023-03-06_ProjetoIBM`

## Testes:

Para verificar a cobertura dos testes, basta clicar com o botão direito sobre o pacote principal da pasta teste, 
selecionar `Modify Run Configuration` em `Code Coverage` selecione o `JaCoCo` clique em `Apply` e `OK`

<img width="383" alt="Screenshot 2023-03-06 050038" src="https://media.github.ibm.com/user/416499/files/65bbf85a-4168-47d2-971a-00b6a70042f1">

Clique novamente com o botão direito sobre o pacote principal da pasta teste e selecione a terceira opção `Run Tests with Coverage` após a
execução será apresentada a cobertura dos testes. 

## Desenvolvido com:<br>

- ``Java 11``- ``SpringBoot 2.7.8``- ``Intellij``- ``Maven 3``- ``Mysql 8``- ``Insomnia``- ``Git``- ``GitHub``

# Autora

| [<img src="https://avatars.github.ibm.com/u/416499" width=115><br><sub>Vanessa Oliveira</sub>](https://github.ibm.com/Van-Oliveira) |
| :---: |
