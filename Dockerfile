FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

RUN useradd -m -s /bin/bash backend

WORKDIR /app

COPY --from=build /build/target/api-0.0.1-SNAPSHOT.jar /app/app.jar

RUN chown -R backend:backend /app

USER backend

EXPOSE 8082

CMD ["java", "-jar", "/app/app.jar"]