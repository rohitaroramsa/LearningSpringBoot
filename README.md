

POST

Example: 
using curl
```
curl -X POST http://localhost:8080/addProduct?productName -H "Content-Type: application/x-www-form-urlencoded" -d "name=Alice&age=25"
```
```
curl -X POST "http://localhost:8080/addProduct?productName=Tv&productCost=100"
```