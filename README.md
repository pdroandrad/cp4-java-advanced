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
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId> <!-- Provides support for the Thymeleaf template engine to render HTML pages on the server side -->
		</dependency>
		</dependency>
</dependencies>
```

## Estrutura do projeto

Construído em Arquitetura **MVC** -  **_Model View Controller_**

``` 
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── MiniMercado
    │   │               ├── config
    │   │               │   ├── DataInitializer.java
    │   │               │   └── SwaggerConfig.java
    │   │               ├── controller
    │   │               │   ├── ProdutoController.java
    │   │               │   └── WebController.java
    │   │               ├── dto
    │   │               │   ├── ProdutoRequestDto.java
    │   │               │   ├── ProdutoResponseDto.java
    │   │               │   └── ProdutoUpdateDto.java
    │   │               ├── exception
    │   │               │   ├── GlobalExceptionHandler.java
    │   │               │   └── ResourceNotFoundException.java
    │   │               ├── MiniMercadoApplication.java
    │   │               ├── model
    │   │               │   └── Produto.java
    │   │               ├── repository
    │   │               │   └── ProdutoRepository.java
    │   │               ├── service
    │   │               │   └── ProdutoService.java
    │   │               └── utils
    │   │                   └── Tamanho.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       │   ├── css
    │       │   │   ├── 404.css
    │       │   │   ├── error.css
    │       │   │   └── style.css
    │       │   └── js
    │       │       └── 404.js
    │       └── templates
    │           ├── error
    │           │   └── 404.html
    │           ├── error.html
    │           ├── index.html
    │           └── produtos
    │               ├── cadastro.html
    │               └── edit.html

```
### **Camadas do projeto — Principais**

- **Camada `CONTROLLER`**  
  Define os *endpoints* da API, mapeando requisições HTTP para métodos Java.  
  Recebe os dados (via *path*, *query params* ou *body*), delega a execução para a camada de serviço e retorna a resposta no formato especificado (JSON, XML, HTML, etc).
  

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

- **Camada `RESOURCES`**
  Contém as configurações do Spring Boot e a implementação das páginas web em Thymeleaf, sendo o diretório `/static` para o .css e o .js e o diretório `/templates` a implementação das páginas em html com Thymeleaf.

### Diagrama - Fluxo da aplicação

![Diagrama](https://github.com/user-attachments/assets/f67379a6-1c0e-4877-a3d6-feb4fefdea19)


## Endpoints, Swagger e Webpage em Thymeleaf

[Swagger](https://cp4-java-advanced-fzrf.onrender.com/swagger-ui/index.html#/)

### Webpage em Thymeleaf, deploy feito no Render:

[Página Inicial - Tranquilo Mercados](https://cp4-java-advanced-fzrf.onrender.com)

Página inicial do projeto "Tranquilo Mercados". A página lista os produtos cadastrados no sistema. No topo da tabela é possível pesquisar o produto pelo tipo dele. 
- (endpoint **`/`** - index.html)

<img width="1599" height="899" alt="pag_inicial" src="https://github.com/user-attachments/assets/6a5d8e4a-386b-4377-9663-c0aae7a74c47" />

Ao clicar no botão "Adicionar Produto" ele redireciona para a página de cadastro de produtos:
- (endpoint **`/produtos/novo`** - cadastro.html)

<img width="1599" height="899" alt="cad_produto" src="https://github.com/user-attachments/assets/c2e11b0f-38e6-49f2-a94e-c766039f6233" />

Voltando na página inicial, se clicarmos em editar, ele redireciona para a página de edição de um produto específico:
- (endpoint **`/produtos/{id}/edit`** - edit.html)

<img width="1599" height="899" alt="edit_produto" src="https://github.com/user-attachments/assets/107b2d1a-7fcb-48b5-8b63-e1b958aac153" />

## Vídeo demonstração

[CP 4 - Java Advanced - Tranquilo Mercados](https://youtu.be/6aGuF5KRSXI)

