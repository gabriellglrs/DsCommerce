# Usa a imagem oficial do Maven para construir o projeto
FROM maven:3.8.5-openjdk-17-slim AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e as dependências para o container
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o código da aplicação para dentro do container
COPY src ./src

# Executa o Maven para construir o projeto e gerar o arquivo .jar
RUN mvn clean package -DskipTests

# --- Fase 2: Criação da imagem final ---

# Usa uma imagem base leve com apenas o JRE
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Copia o arquivo .jar gerado na fase anterior para a imagem final
COPY --from=build /app/target/dscommerce-0.0.1-SNAPSHOT.jar /app/app.jar

# Copia o arquivo import.sql para o local adequado do Hibernate
COPY src/main/resources/import.sql /app/import.sql

# Expõe a porta que sua aplicação Spring Boot usará
EXPOSE 8080

# Define o comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
