
# Project for learning microservice architecture.
e-commerce platform for diagon alley (HarryPotter universum :magic_wand: :sparkler:)
## Technologies:
* Java 17
* Maven multi modules
* Spring Boot 3.1
* Spring Security 6.1
* Spring Cloud 2022.0.3
* Docker
* MongoDb
* PostgreSQL
* Keycloak
* Kafka
* resilience4j
* RabbitMQ
* Zipkin

# Endpoints
To check service you can do:
* http://localhost:8181/api/v1/order/status
* http://localhost:8181/api/v1/inventory/status
* http://localhost:8181/api/v1/customer/status
* http://localhost:8181/api/v1/fraud/status
* http://localhost:8181/api/v1/product/status

***Customer***
* **POST**: http://localhost:8181/api/v1/customer
>{
"firstName":"firstName",
"lastName":"lastName",
"email":"email@gmail.com",
"password":"password",
"dateOfBirth":"2000-01-01"
}
* **GET**: http://localhost:8181/api/v1/customer

***Inventory***
* **GET**: http://localhost:8181/api/v1/inventory?skuCode=iphone_13&skuCode=iphone13_red

***Order***
* **POST**: http://localhost:8181/api/v1/order
>{
"orderLineItemsDtoList"  :  [{
"id":  "1",
"skuCode":  "iphone_13",
"price":  "1000",
"quantity":  "1"
}]}

## Postman Authorization
For secured endpoint locally:

![locally](https://github.com/bgnatowski/microservices-diagon/img/security_local.png)
(regenerate client-secret in keycloak admin panel)

For secured endpoint docker (postman Authorization):

![docker](https://github.com/bgnatowski/microservices-diagon/img/security_docker.png)
(regenerate client-secret in keycloak admin panel)

## Run locally
You can start services in two ways:
### 1. All services + tools via docker container
Add  mapping to your hosts file in windows: *C:\Windows\System32\drivers\etc\hosts*
It should be like so:
```java
127.0.0.1 keycloak
```
Then make sure that everything is uncommented in *docker-compose.yml*
Next you can simply type on the root of the project:
```java
docker-compose up -d 
```
Make sure that everything started fine (sometimes you have to restart some services; api-gateway should start after discovery-server)

### 2. Common tools via docker container, services locally
Make sure to comment everything from: **discovery-server to notification-service** in *docker-compose.yml*
Next you can simply type on the root of the project:
```java
docker-compose up -d 
```
Then start all services in IDE
> Make sure to start from ***discovery-server***, then rest of services and at the end ***api-gateway***