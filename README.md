# Aves-Server

PoC for migrating the aves server to a kotlin based service stack.

## Architecture

We are using a DDD-like architecture, but without the burden of defining a full DDD model (involving domains "experts"
and so on). The idea is to have a clear separation of concerns between the different layers of the application.
So each layer does the following:

- **Application**: Exposes the REST API and handles the HTTP requests, it's what the clients see.
- **Domain**: Contains the business logic, domain core entities, this layer is "clean" in other words, doesn't have any
  dependency on other layers or frameworks. To access the logic, we provide UseCases, so we can group them (kind of
  services + aggregates in DDD)
- **Infrastructure**: Contains the implementation of the domain repositories, and other external dependencies, like
  databases, queues technologies, framework configurations, etc.

<img src="https://imgpile.com/images/C7Q2Gj.png" width="800"/>

## Build it

```
make docker/build-image
```

## Run it

```
make compose/run
```

## API Documentation

http://localhost:8090/swagger-ui/index.html

## Web Client (Pending)

https://app.services.zinfra.io

## Mobile Client (Pending)

wire://access/?config=https://aves.services.zinfra.io


