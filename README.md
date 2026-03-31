# Quantity Management

A Spring Boot REST API for performing unit operations across measurement types including LENGTH, WEIGHT, VOLUME, and TEMPERATURE.
Supports conversion, comparison, addition, subtraction, and division of quantities across compatible units such as METER, CENTIMETER, KILOGRAM, GRAM, LITER, and CELSIUS.
All operations are persisted to an H2 in-memory database, making it easy to track operation history without any external database setup.
The API follows a clean layered architecture with DTOs, a service interface, and JPA repositories, and is tested using Mockito unit tests and Spring MockMvc integration tests.
Swagger UI is available at `/swagger-ui/index.html` for exploring and testing all endpoints interactively.
