FROM openjdk:11.0.10-slim
WORKDIR /quarkus-app/
COPY pom.xml ./
COPY mvnw ./
COPY .mvn/wrapper/ ./.mvn/wrapper/
RUN ./mvnw clean install
COPY . .
RUN ./mvnw clean package -Dmaven.test.skip=true
ENTRYPOINT ["java", "-jar", "target/leo-iot-server-1.0-SNAPSHOT-runner.jar"]
