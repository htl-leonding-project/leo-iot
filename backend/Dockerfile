FROM maven:3.8.4-openjdk-11-slim as build

RUN curl --location --output graalvm.tgz https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.3.4/graalvm-ce-java11-linux-amd64-20.3.4.tar.gz \
    && tar -xzf graalvm.tgz \
    && mv graalvm-* /opt/graalvm \
    && /opt/graalvm/bin/gu install native-image \
    && apt update \
    && apt install build-essential libz-dev zlib1g-dev -y

WORKDIR /app

COPY pom.xml .

RUN mvn install

COPY . .

RUN export GRAALVM_HOME=/opt/graalvm && mvn verify -Pnative -DskipTests

FROM ubuntu:impish-20211102 as run

RUN apt update -y

WORKDIR /app

COPY --from=build /app/target/*-runner /app/application

EXPOSE 8080

CMD ["./application"]
