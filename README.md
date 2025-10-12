# Self Learning practice of Spring Boot

## Basic of Spring Boot to Practice
Most basic thing about Spring Boot repo structure:
- controller package: defines entry points
- service: controllers call the service it depends on
- model: database/Datastructure model
- repository: extends JpaRepository to access CRUD functions
- App class to contain @SpringBootApplication annotation, and  SpringApplication.run(YourApp.class, args);

delete jpa call needed @Transactional

POST

Example: 
using curl

```
curl -X POST "http://localhost:8080/shop/products?productName=Tv&productCost=100"
```

### get all products
```
curl -X GET "http://localhost:8080/shop/products/all"
```

### Deletion curl 
```
curl -X DELETE "http://localhost:8080/shop/products/mobile"
```

### GET Shop
```shell
curl -X GET "http://localhost:8080/shop/shop"
```

```shell
curl -X GET "http://localhost:8080/shop/products/mobile"
```
