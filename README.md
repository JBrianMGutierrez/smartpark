## Requirements

- Java 17  
- Maven 3.6+  
- Spring Boot 3.5.11  
- H2 in-memory database  

---

## Build & Run

1. **Clone the repository**

```bash
git clone https://github.com/JBrianMGutierrez/smartpark
cd smartpark

mvn clean install
mvn spring-boot:run

The application will start on http://localhost:8080.
API are in "smartpark.postman_collection.json" root

## Preloaded Data

The application preloads sample data using data.sql
Vehicles are initially not parked in any lot.