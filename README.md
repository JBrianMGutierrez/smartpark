## Table of Contents

- [Requirements](#requirements)  
- [Build & Run](#build--run)  
- [API Endpoints](#api-endpoints)  
- [Testing](#testing)  
- [Preloaded Data](#preloaded-data)  

---

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
API's are in "smartpark.postman_collection.json" root

## Preloaded Data

The application preloads sample data using data.sql
Vehicles are initially not parked in any lot.