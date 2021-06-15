FROM openjdk:11.0.10-slim
WORKDIR /quarkus-app/
COPY .mvn/wrapper/ ./.mvn/wrapper/
COPY pom.xml mvnw ./
RUN ./mvnw clean install
COPY . .
RUN ./mvnw clean package -Dmaven.test.skip=true && \
    mv target/leo-iot-server-*-SNAPSHOT-runner.jar target/quarkus-app.jar
ENTRYPOINT ["java", "-jar", "target/quarkus-app.jar"]
