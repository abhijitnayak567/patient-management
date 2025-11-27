FROM maven:3-amazoncorretto-25 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package

FROM amazoncorretto:25-alpine AS runner
WORKDIR /app
COPY --from=builder ./app/target/billing-service-0.0.1.jar ./app.jar

EXPOSE 4001
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]