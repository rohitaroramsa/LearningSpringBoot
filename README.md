# Self Learning practice of Spring Boot

## Basic of Spring Boot to Practice
Most basic thing about Spring Boot repo structure:
- controller package: defines entry points
- service: controllers call the service it depends on
- model: database/Datastructure model
- repository: extends JpaRepository to access CRUD functions
- App class to contain @SpringBootApplication annotation, and  SpringApplication.run(YourApp.class, args);

POST

Example: 
using curl
```
curl -X POST http://localhost:8080/addProduct?productName -H "Content-Type: application/x-www-form-urlencoded" -d "name=Alice&age=25"
```
```
curl -X POST "http://localhost:8080/addProduct?productName=Tv&productCost=100"
```