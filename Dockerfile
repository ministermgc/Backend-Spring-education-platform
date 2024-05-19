FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /app
COPY src src
COPY pom.xml pom.xml
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jammy
RUN addgroup spring-boot-group && adduser --ingroup spring-boot-group spring-boot
USER spring-boot:spring-boot-group
WORKDIR /app
COPY --from=build /app/target/*.jar code-nest.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "code-nest.jar"]