## Dependências do projeto

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<scope>runtime</scope>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>com.oracle.database.jdbc</groupId>
		<artifactId>ojdbc11</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		<version>2.7.0</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	<dependency>
		<groupId>org.thymeleaf.extras</groupId>
		<artifactId>thymeleaf-extras-springsecurity6</artifactId>
		<version>3.1.1.RELEASE</version>
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
│   │   │               ├───config
│   │   │               │       DataInitializer.java
│   │   │               │       SecurityConfig.java
│   │   │               │       SwaggerConfig.java
│   │   │               │
│   │   │               ├───controller
│   │   │               │       AuthController.java
│   │   │               │       ProdutoController.java
│   │   │               │       WebController.java
│   │   │               │
│   │   │               ├───dto
│   │   │               │       ProdutoRequestDto.java
│   │   │               │       ProdutoResponseDto.java
│   │   │               │       ProdutoUpdateDto.java
│   │   │               │
│   │   │               ├───exception
│   │   │               │       GlobalExceptionHandler.java
│   │   │               │       ResourceNotFoundException.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       Produto.java
│   │   │               │       Usuario.java
│   │   │               │
│   │   │               ├───repository
│   │   │               │       ProdutoRepository.java
│   │   │               │       UsuarioRepository.java
│   │   │               │
│   │   │               ├───service
│   │   │               │       ProdutoService.java
│   │   │               │       UserDetailServiceImpl.java
│   │   │               │       UsuarioService.java
│   │   │               │
│   │   │               └───utils
│   │   │                       Tamanho.java
│   │   │
│   │   └───resources
│   │       │   application.properties
│   │       │   env.properties
│   │       │
│   │       ├───static
│   │       │   ├───css
│   │       │   │       404.css
│   │       │   │       error.css
│   │       │   │       style.css
│   │       │   │
│   │       │   └───js
│   │       │           404.js
│   │       │
│   │       └───templates
│   │           │   error.html
│   │           │   index.html
│   │           │   login.html
│   │           │   signup.html
│   │           │
│   │           ├───admin
│   │           ├───error
│   │           │       401.html
│   │           │       403.html
│   │           │       404.html
│   │           │
│   │           └───produtos
│   │                   cadastro.html
│   │                   edit.html
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

### Diagrama - Fluxo da aplicação - Geral

![Diagrama](https://github.com/user-attachments/assets/f67379a6-1c0e-4877-a3d6-feb4fefdea19)

## Endpoints - Swagger 

[Swagger](https://cp5-java-advanced.onrender.com/swagger-ui/index.html)

### Webpage em Thymeleaf, deploy feito no Render:

[Tranquilo Mercados](https://cp5-java-advanced.onrender.com/login)

Página inicial - Login
- (endpoint **`/login` - login.html)
Usuários cadastrados na execução do script `src/config/DataInitializer.java`:

| Usuário  | Senha | Role | Privilégios |
| ------- | ----- | ---- |----------- |
| admin    | senha123 | ROLE_ADMIN  | Acesso à todas as páginas |
| estoquista | senha321 | ROLE_ESTOQUISTA | Acesso ao painel principal (`index.html`) e página de cadastro de produtos (`./produtos/cadastro.html`) |   

<img width="1911" height="730" alt="image" src="https://github.com/user-attachments/assets/7c70a6ab-0768-4a29-bdc8-dae7f733db5d" />

Crie uma conta redireciona para a página de Sign Up:

Página de cadastro de usuário - Sign up
- (endpoint **`/signup` - signup.html)
  
<img width="1915" height="788" alt="image" src="https://github.com/user-attachments/assets/8ae87af4-6fbb-4868-a1fd-454d5e905b68" />

É possível cadastrar usuários com duas roles diferentes

Homepage do projeto "Tranquilo Mercados". A página lista os produtos cadastrados no sistema. No topo da tabela é possível pesquisar o produto pelo tipo dele. 
- (endpoint **`/`** - index.html)
  
<img width="1599" height="899" alt="pag_inicial" src="https://github.com/user-attachments/assets/6a5d8e4a-386b-4377-9663-c0aae7a74c47" />

Ao clicar no botão "Adicionar Produto" ele redireciona para a página de cadastro de produtos:
- (endpoint **`/produtos/novo`** - cadastro.html)

<img width="1599" height="899" alt="cad_produto" src="https://github.com/user-attachments/assets/c2e11b0f-38e6-49f2-a94e-c766039f6233" />

Voltando na página inicial, se clicarmos em editar, ele redireciona para a página de edição de um produto específico:
- (endpoint **`/produtos/{id}/edit`** - edit.html)

<img width="1599" height="899" alt="edit_produto" src="https://github.com/user-attachments/assets/107b2d1a-7fcb-48b5-8b63-e1b958aac153" />

## Vídeo demonstração

[Java Advanced - Tranquilo Mercados](https://youtu.be/6aGuF5KRSXI)

