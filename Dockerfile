FROM maven:3.8.5-openjdk-17 AS BUILD

LABEL slashicorp <devops@slashicorp.com.br>

COPY . .

RUN mvn package -DskipTests

FROM adoptopenjdk:17-jre

ENV _JAVA_OPTIONS="-Djava.net.preferIPv4Stack=true"

COPY --from=BUILD target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]