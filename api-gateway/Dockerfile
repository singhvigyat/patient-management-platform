FROM maven:3.9.9-eclipse-temurin-24 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B # this means cache unless a change has been detected.

COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:24-jre AS runner

WORKDIR /app

COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4004

ENTRYPOINT ["java", "-jar", "app.jar"]