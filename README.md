<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=4C89F8&height=120&section=header"/>

![LinkedIn cover - 26](https://github.com/user-attachments/assets/b1da02dc-153e-47fe-84d1-85a2568a85e4)


# DSCommerce

Uma aplicação de e-commerce desenvolvida em Spring Boot com implementação de servidor de autorização OAuth2, autenticação customizada com password grant type e configuração de segurança abrangente.

## 🚀 Funcionalidades

- **Servidor de Autorização OAuth2**: Implementação customizada com suporte a tokens JWT
- **Password Grant Customizado**: Implementação personalizada do OAuth2 password grant type
- **Configuração de Segurança**: Setup de segurança completo com suporte a CORS
- **Tratamento de Exceções**: Handler global de exceções com respostas de erro customizadas
- **Autenticação JWT**: Tokens JWT auto-contidos com claims customizadas
- **Autorização Baseada em Funções**: Gerenciamento de autoridades e escopos de usuários

## 🛠 Tecnologias

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security OAuth2 Authorization Server**
- **Spring Data JPA**
- **JWT (JSON Web Tokens)**
- **Criptografia BCrypt**
- **Lombok**
- **Maven**
- **MySQL**
- **Docker & Docker Compose**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose
- MySQL (via Docker)

## ⚙️ Configuração

### Configuração do Banco de Dados

Crie um arquivo `application.properties` com as seguintes configurações:

```properties
# Configuração do Banco de Dados (MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/dscommerce
spring.datasource.username=dscommerce
spring.datasource.password=dscommerce123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

# Configuração de Segurança
security.client-id=dscommerce-client
security.client-secret=dscommerce-secret
security.jwt.duration=86400

# Configuração CORS  
cors.origins=http://localhost:3000,http://localhost:4200,http://localhost:8081
```

### Recursos de Segurança

#### Servidor de Autorização OAuth2
- Implementação customizada do password grant type
- Geração de tokens JWT com pares de chaves RSA
- Registro de clientes em memória
- Claims customizadas no token (username, authorities)

#### Servidor de Recursos
- Validação de tokens JWT
- Configuração CORS para requisições cross-origin
- Segurança habilitada a nível de método
- Mapeamento customizado de autoridades

## 🔐 Fluxo de Autenticação

### Password Grant Type

```http
POST /oauth2/token
Content-Type: application/x-www-form-urlencoded
Authorization: Basic <base64(client_id:client_secret)>

grant_type=password&username=usuario@exemplo.com&password=senha123&scope=read write
```

### Resposta

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 86400,
  "scope": "read write"
}
```

### Claims do Token JWT

```json
{
  "sub": "client-id",
  "aud": ["client-id"],
  "username": "usuario@exemplo.com",
  "authorities": ["ROLE_USER", "ROLE_ADMIN"],
  "scope": ["read", "write"],
  "iss": "http://localhost:8080",
  "exp": 1640995200,
  "iat": 1640908800
}
```

## 🏗 Estrutura do Projeto

```
src/main/java/com/devsuperior/dscommerce/
├── Start.java                              # Classe principal da aplicação
├── config/security/
│   ├── AuthorizationServerConfig.java      # Configuração do Servidor de Autorização OAuth2
│   ├── ResourceServerConfig.java           # Configuração do Servidor de Recursos
│   └── customgrant/
│       ├── CustomPasswordAuthenticationConverter.java
│       ├── CustomPasswordAuthenticationProvider.java
│       ├── CustomPasswordAuthenticationToken.java
│       └── CustomUserAuthorities.java
├── exceptions/
│   ├── DatabaseException.java
│   ├── ForbbidenException.java
│   ├── ResourceNotFoundException.java
│   ├── UsernameNotFoundException.java
│   └── handler/
│       ├── GlobalExceptionHandler.java
│       └── error/
│           ├── CustomError.java
│           ├── FieldError.java
│           └── ValidationError.java
└── projections/
    └── UserDetailsProjection.java
```

## 🐳 Configuração Docker

### Pré-requisitos Docker
- Docker
- Docker Compose

### Configuração do Banco de Dados com Docker

O projeto inclui configuração Docker para banco de dados MySQL:

#### docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: dscommerce-mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: dscommerce
      MYSQL_USER: dscommerce
      MYSQL_PASSWORD: dscommerce123
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - dscommerce-network

  app:
    build: .
    container_name: dscommerce-app
    restart: always
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/dscommerce
      - SPRING_DATASOURCE_USERNAME=dscommerce
      - SPRING_DATASOURCE_PASSWORD=dscommerce123
    depends_on:
      - mysql
    networks:
      - dscommerce-network

volumes:
  mysql_data:

networks:
  dscommerce-network:
    driver: bridge
```

#### Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar Maven wrapper e pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Tornar mvnw executável
RUN chmod +x ./mvnw

# Baixar dependências
RUN ./mvnw dependency:go-offline -B

# Copiar código fonte
COPY src src

# Construir aplicação
RUN ./mvnw clean package -DskipTests

# Expor porta
EXPOSE 8080

# Executar aplicação
CMD ["java", "-jar", "target/dscommerce-0.0.1-SNAPSHOT.jar"]
```

### Executando com Docker

1. **Clonar o repositório**
```bash
git clone <url-do-repositorio>
cd dscommerce
```

2. **Iniciar os serviços**
```bash
docker-compose up -d
```

3. **Verificar logs**
```bash
docker-compose logs -f app
```

4. **Parar serviços**
```bash
docker-compose down
```

### Propriedades da Aplicação para Docker

Criar `application-docker.properties`:

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:mysql://mysql:3306/dscommerce
spring.datasource.username=dscommerce
spring.datasource.password=dscommerce123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuração de Segurança
security.client-id=dscommerce-client
security.client-secret=dscommerce-secret
security.jwt.duration=86400

# Configuração CORS
cors.origins=http://localhost:3000,http://localhost:4200,http://localhost:8081
```

## 🚀 Começando

### Desenvolvimento Local

1. **Clonar o repositório**
```bash
git clone <url-do-repositorio>
cd dscommerce
```

2. **Iniciar MySQL com Docker**
```bash
docker-compose up -d mysql
```

3. **Configurar propriedades da aplicação**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dscommerce
spring.datasource.username=dscommerce
spring.datasource.password=dscommerce123
```

4. **Executar a aplicação**
```bash
./mvnw spring-boot:run
```

### Desenvolvimento com Docker

1. **Construir e executar tudo**
```bash
docker-compose up --build
```

2. **Aplicação estará disponível em:**
- Aplicação: http://localhost:8080
- MySQL: localhost:3306

## 📊 Endpoints da API

### Endpoints de Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/oauth2/token` | Obter token de acesso |

### Recursos Protegidos

Todos os outros endpoints requerem autenticação Bearer token:

```http
Authorization: Bearer <access_token>
```

## 🔧 Desenvolvimento

### Executando Testes

```bash
# Executar todos os testes
./mvnw test

# Executar testes com cobertura
./mvnw test jacoco:report
```

### Construindo a Aplicação

```bash
# Limpar e construir
./mvnw clean package

# Pular testes
./mvnw clean package -DskipTests
```

### Migração do Banco de Dados

A aplicação usa auto-update do Hibernate DDL. Para produção, considere usar Flyway ou Liquibase:

```properties
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

## 🔒 Recursos de Segurança

### Fluxo de Password Grant Customizado
- Valida username e password contra UserDetailsService
- Gera tokens JWT com claims customizadas
- Suporta autorização baseada em escopo
- Manipulação thread-safe do contexto de autenticação

### Tratamento de Exceções
- Handler global de exceções para respostas de erro consistentes
- Exceções customizadas para diferentes cenários de erro
- Tratamento de erros de validação com detalhes a nível de campo

### Configuração CORS
- Origens permitidas configuráveis
- Suporte para credenciais
- Métodos HTTP e cabeçalhos flexíveis

## 📝 Exemplos de Uso

### Obtendo Token de Acesso

```bash
curl -X POST http://localhost:8080/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -H "Authorization: Basic $(echo -n 'dscommerce-client:dscommerce-secret' | base64)" \
  -d "grant_type=password&username=usuario@exemplo.com&password=123456&scope=read write"
```

### Usando Token de Acesso

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## 🐛 Solução de Problemas

### Problemas Comuns

1. **Conexão com Banco de Dados**
   - Certifique-se de que o MySQL está rodando
   - Verifique as credenciais do banco
   - Confirme se o banco de dados existe

2. **Problemas com Token JWT**
   - Verifique expiração do token
   - Confirme credenciais do cliente
   - Certifique-se do formato Bearer correto

3. **Erros CORS**
   - Atualize a propriedade `cors.origins`
   - Verifique requisições preflight
   - Confirme cabeçalhos permitidos

### Logs

Habilitar logging debug para segurança:

```properties
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.security.oauth2=DEBUG
```

## 🤝 Contribuindo

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/funcionalidade-incrivel`)
3. Commit suas mudanças (`git commit -m 'Adiciona funcionalidade incrível'`)
4. Push para a branch (`git push origin feature/funcionalidade-incrivel`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🔗 Links Úteis

- [Spring Security OAuth2 Authorization Server](https://spring.io/projects/spring-authorization-server)
- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [JWT.io](https://jwt.io/) - Debugger de tokens JWT
- [Documentação Docker](https://docs.docker.com/)

## 📞 Suporte

Para suporte e dúvidas:
- Crie uma issue no repositório
- Consulte a documentação existente
- Revise o Stack Overflow para soluções comuns

----

## Autor
 Gabriel lucas rodrigues souza
 <br>
LinkedIn: https://www.linkedin.com/in/gabriellglrs/
 <br>

 <br>
<div align="center">
  <img src="https://github.com/user-attachments/assets/ed7208b8-6bdc-4c82-98aa-8c8cb9c1428f" height="150"/>
</div>

<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=4C89F8&height=120&section=footer"/>
