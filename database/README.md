# IoTServerDB
IoTServerDB is the database for the IoTServer network

![ERD](https://github.com/andileeb/IoTServerDB/blob/master/images/ERD.png)

## ACL Rules

| Access | Meaning |
|---|-----------|
| 1 | Subscribe |
| 2 | Publish   |
| 3 | PubSub    |

Note: if you want that a user can ONLY subscribe to a topic you have to deny that the client can publish with an extra rule. Explicit definition needed!

## Deployment
### Git tag

Add a git Tag

```bash
git tag -a v0.0.0 -m "my release notes"
git push origin v0.0.0
```

### Docker build

> For `TAG` use the same you used during the _Git tag_ step!


```bash
docker login registry.gitlab.com
docker build --compress -t registry.gitlab.com/andileeb/iotserverregistry/iotserverdb:TAG .
docker push registry.gitlab.com/andileeb/iotserverregistry/iotserverdb:TAG
```

### Docker run

```bash
docker run -d --name=iotserverdb -p 5432:5432 -e POSTGRES_PASSWORD=password registry.gitlab.com/andileeb/iotserverregistry/iotserver:TAG
```
