## Dependências do projeto

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId> <!-- Entity persistence -->
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId> <!-- Brings RESTful service libraries -->
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId> <!-- Development tools -->
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId> <!-- Oracle Database connector -->
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>  <!-- Generates getters and setters, constructors, etc  -->
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId> <!-- Tests  -->
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId> <!-- Validates request data using Jakarta Bean Validation (@NotNull, @Size, @Email) -->
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>  <!-- Generates OpenAPI/Swagger documentation and provides Swagger UI for API testing -->
			<version>2.7.0</version>
		</dependency>
</dependencies>
```

## Estrutura do projeto

Construído em Arquitetura **MVC** -  **_Model View Controller_**

``` 
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           └───MiniMercado
│   │   │               │   MiniMercadoApplication.java
│   │   │               │
│   │   │               ├───controller
│   │   │               │       ProdutoController.java
│   │   │               │
│   │   │               ├───dto
│   │   │               │       ProdutoRequestDto.java
│   │   │               │       ProdutoResponseDto.java
│   │   │               │
│   │   │               ├───exception
│   │   │               │       GlobalExceptionHandler.java
│   │   │               │       ResourceNotFoundException.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       Produto.java
│   │   │               │
│   │   │               ├───repository
│   │   │               │       ProdutoRepository.java
│   │   │               │
│   │   │               ├───service
│   │   │               │       ProdutoService.java
│   │   │               │
│   │   │               └───utils
│   │   │                       Tamanho.java
│   │   │
│   │   └───resources
│   │           application.properties
```
### **Camadas do projeto — Principais**

- **Camada `CONTROLLER`**  
  Define os *endpoints* da API, mapeando requisições HTTP para métodos Java.  
  Recebe os dados (via *path*, *query params* ou *body*), delega a execução para a camada de serviço e retorna a resposta no formato especificado (JSON, XML, etc.).

- **Camada `DTO`** (*Data Transfer Object*)  
  Define os formatos de entrada (*Request DTO*) e saída (*Response DTO*) para isolar o modelo de dados interno e controlar o que é exposto ou recebido pela API.

- **Camada `EXCEPTION`**  
  Centraliza o tratamento de erros e exceções, garantindo respostas consistentes e adequadas para o cliente.

- **Camada `MODEL`**  
  Contém as entidades de domínio mapeadas para o banco de dados (via *JPA/Hibernate annotations*). Representa a estrutura real da tabela.

- **Camada `REPOSITORY`**  
  Responsável pelo acesso e manipulação de dados no banco.  
  Estende interfaces do Spring Data JPA, evitando implementação manual de consultas simples.

- **Camada `SERVICE`**  
  Contém a lógica de negócio da aplicação.  
  Atua como intermediária entre o `Controller` e o `Repository`, garantindo que as regras de negócio sejam respeitadas antes de acessar ou modificar dados.

### Diagrama - Fluxo da aplicação

![Diagrama](https://github.com/user-attachments/assets/f67379a6-1c0e-4877-a3d6-feb4fefdea19)


## Endpoints & Swagger

<!-- Link para o Swagger no Render -->

<!-- Detalhamento dos endpoints -->
  
