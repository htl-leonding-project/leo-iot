# LEO IOT

## Description

Leo IOT is a project created to measure various real world data
such as temperature and air moisture which are collected from sensor
boxes placed in the rooms of the school building. The application
will display the data on a dashboard and a 3D-Modell.

## Documents

- [Index](https://htl-leonding-project.github.io/leo-iot/)

## Associated repositories

- [Smallrye MQTT Prototype](https://github.com/QuirinEcker/quarkus-mqtt)
- [Camel Paho MQTT Prototype](https://github.com/QuirinEcker/camel-paho-demo)
- [Dashboard](https://github.com/sknogler/leoiot-new-dashboard)

## Getting Started Developement

### Start Timescale DB

```shell
cd database
docker-compose up -d
```

### Start Quarkus in development Mode

```shell
cd backend
./mvnw clean compile quarkus:dev
```

### Start Angular in development Mode

```shell
cd frontend
npm start
```

## Gettign Started Production

### Build

```shell
docker-compose build
```

### Start

```shell
docker-compose up -d
```

NOTE: -d option is for daemon. If you don't want to start it in the background remove this option.

### Push and Pull

```
docker-compose push
docker-compose pull
```

## Archiv

- [Previous Version](https://drive.google.com/drive/folders/1sIm3kAN1Gty35lSp5xbqtrQ_-PelBX2N)
