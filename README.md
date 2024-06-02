# Products API
## Overview

This project provides an API to manage products from an XML file. The API offers endpoints to read the XML file, list all products, and fetch a product by its name. The solution is implemented in Java using Spring Boot.

## Features

- **Read an XML file** and respond with the number of records found.
- **Return a list of all products** in JSON format.
- **Return a specific product by name** in JSON format.
- **Unit tests** for the API endpoints.
- **API documentation** using Swagger.

## Requirements
Java: Installed version of Java compatible with Spring Boot 2.6.4 requirements.
Spring Boot: Utilizes Spring Boot version 2.6.4. Spring Boot is a framework for building Java-based applications.
Spring Boot Starter Web: Uses spring-boot-starter-web for handling the HTTP layer and providing API endpoints.
Spring Boot Starter Test: Utilizes spring-boot-starter-test for testing Spring Boot applications.
Springdoc OpenAPI UI: Utilizes springdoc-openapi-ui for generating an API interface compatible with OpenAPI.
Jackson Core, Jackson Databind, Jackson Dataformat XML: Utilizes the Jackson library for processing JSON and XML data.

## Installation
Clone the repository.
It was writen in inteliJ using gradle.

## Usage 
Place your XML file with product records in a known location.
Use the provided API endpoints to interact with the product data ( http://localhost:8080/*endpoint*)
You can also open Swagger UI ( http://localhost:8080/swagger-ui/index.html#/ ) after running src/main/java/api/Main in the project to easly use endpoints.

![image](https://github.com/MartynaSokolowska/springProductsApi/assets/115418969/ef62a2bd-8674-4c6f-94f2-857e92cb252f)

#### Endpoint POST /api/products/handleXMLUploadAndParse
Provide the path to the file to load data from it. the number of read products is returned.

#### Endpoint GET /api/products
Returns JSON with products.

#### Endpoint GET /api/products/{name}
Returns JSON with product under given name.

## Example file
![image](https://github.com/MartynaSokolowska/springProductsApi/assets/115418969/5494b7e0-f561-4769-b4e1-1162db80d086)

