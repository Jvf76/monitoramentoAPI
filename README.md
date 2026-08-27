Monitoramento Industrial
Sistema desenvolvido para o desafio técnico da vaga de estágio em desenvolvimento da Setta Digital Labs.

A aplicação simula a leitura de temperatura de uma máquina industrial, calcula sua eficiência, identifica leituras anormais, armazena os dados no MySQL e apresenta a leitura atual e o histórico em uma interface web.

Funcionalidades
Simulação de temperatura entre 15 °C e 40 °C
Cálculo da eficiência da máquina
Identificação de leituras anormais
Armazenamento de data, hora, temperatura e eficiência
Exibição da leitura mais recente
Histórico ordenado da leitura mais recente para a mais antiga
Tratamento de erros sem interromper a tela
Interface adaptada para computadores e dispositivos móveis
Testes unitários da regra de eficiência
Regra de eficiência
A eficiência é calculada conforme as seguintes regras:

Temperatura abaixo de 21 °C: eficiência de 23%
Temperatura igual ou superior a 32 °C: eficiência de 100%
Entre 21 °C e 32 °C: cada grau acima de 21 °C acrescenta 7 pontos à eficiência
A fórmula usada no intervalo é:

eficiência = 23 + (temperatura - 21) × 7
Uma leitura é considerada anormal quando sua eficiência está abaixo de 50%.

Tecnologias utilizadas
Java 17
Spring Boot
Spring MVC
Spring Data JPA
Thymeleaf
MySQL
Lombok
JUnit
Maven
O Spring Boot foi escolhido por facilitar a organização da aplicação em camadas e a integração com o banco de dados. O Thymeleaf permite criar uma interface web usando o mesmo projeto Java, enquanto o Spring Data JPA reduz a necessidade de escrever consultas SQL para operações básicas.

Pré-requisitos
Antes de executar o projeto, é necessário possuir:

Java 17
MySQL 8
Maven ou Maven Wrapper
MySQL Workbench opcionalmente
IntelliJ IDEA ou outra IDE Java
Configuração do banco de dados
Crie o banco executando:

CREATE DATABASE IF NOT EXISTS monitoramento_industrial
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
A tabela leituras é criada ou atualizada automaticamente pelo Hibernate ao iniciar a aplicação.

Também existe um script SQL na pasta database para criação manual da estrutura.

Configuração da senha
A senha do MySQL não é armazenada no código. A aplicação utiliza a variável de ambiente:

DB_PASSWORD
No IntelliJ:

Acesse Run → Edit Configurations.
Selecione MonitoramentoApplication.
Abra Environment variables.
Adicione DB_PASSWORD com a senha do usuário root.
Como executar
Clone ou extraia o projeto.
Crie o banco monitoramento_industrial.
Configure a variável DB_PASSWORD.
Execute a classe MonitoramentoApplication.
Acesse:
http://localhost:8080/
Para gerar uma nova leitura, clique no botão Atualizar leitura.

Estrutura dos dados
As leituras são armazenadas na tabela leituras.

Campo	Descrição
id	Identificador gerado automaticamente
data_hora	Data e hora da leitura
temperatura	Temperatura simulada
eficiencia	Eficiência calculada
anormal	Indica se a eficiência está abaixo de 50%
Origem da temperatura
A temperatura foi simulada por meio da classe TemperaturaService, utilizando valores aleatórios entre 15 °C e 40 °C.

A simulação foi escolhida para priorizar a regra de negócio principal e evitar dependência de conexão com uma API externa durante a demonstração. A classe responsável pela geração foi separada para permitir uma futura substituição por uma API ou sensor real.

Tratamento de erros
O fluxo de atualização possui tratamento de exceções. Caso ocorra uma falha ao obter ou salvar uma leitura, a aplicação redireciona o usuário para a página principal e apresenta uma mensagem amigável, mantendo a tela disponível.

Limitações conhecidas
A temperatura é simulada e não vem de um sensor real.
O sistema monitora somente uma máquina.
O banco está configurado para execução local.
Não existe autenticação de usuários.
O histórico ainda não possui paginação.
Não foi implementado gráfico de linha ou atualização automática.
Raciocínio e visão
E se fossem 100 máquinas enviando leituras a cada cinco minutos?
Seria necessário identificar cada máquina e sensor no banco de dados. Cem máquinas gerariam 28.800 leituras por dia, então a consulta de todo o histórico sem paginação poderia ficar lenta e consumir muita memória.

Eu adicionaria índices por máquina e data, paginação no histórico e processamento das leituras em segundo plano. Se o volume aumentasse, também avaliaria o uso de filas para receber as leituras sem depender do processamento imediato da interface.

Quais dados seriam úteis para prever falhas?
Além da temperatura e eficiência, seria útil armazenar o identificador e modelo da máquina, carga de trabalho, tempo de funcionamento, vibração, pressão, rotação, consumo de energia, estados de operação e alarmes.

Também seria importante registrar manutenções e falhas confirmadas. Esses registros permitiriam relacionar o comportamento dos sensores com eventos reais e seriam usados como exemplos para um futuro modelo de previsão.

Qual melhoria seria feita com mais tempo?
Eu substituiria a simulação por uma integração com um sensor ou API real, mantendo a mesma separação de responsabilidades. Também adicionaria mecanismos de repetição e timeout para que uma falha temporária na fonte de temperatura não interrompesse o monitoramento.

Testes
A regra de cálculo de eficiência possui testes unitários para:

temperatura abaixo de 21 °C;
temperatura igual a 21 °C;
temperatura entre os limites;
temperatura igual a 32 °C;
temperatura acima de 32 °C.
Para executar os testes:

mvnw.cmd test
