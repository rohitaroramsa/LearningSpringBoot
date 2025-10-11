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
curl -X POST http://localhost:8080/add-product?productName -H "Content-Type: application/x-www-form-urlencoded" -d "name=Alice&age=25"
```
```
curl -X POST "http://localhost:8080/add-product?productName=Tv&productCost=100"
```

### get all products
```
curl -X GET "http://localhost:8080/getAllProduct"
```

### Deletion curl 
```
curl -X DELETE "http://localhost:8080/delete-product?productName=radio"
```
