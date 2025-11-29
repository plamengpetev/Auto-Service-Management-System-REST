# Mechanic Assignment Service (REST)

MechanicAssignmentService - REST Microservice
Overview
MechanicAssignmentService is a standalone Spring Boot REST microservice responsible for
managing mechanics within the Auto Service Management System. It provides mechanic
availability, automatic assignment, and detail retrieval. The main application communicates with this
service through OpenFeign.
Architecture Overview
The architecture consists of a Main Application (UI, business logic, security) and this REST
microservice (mechanic management, JSON APIs). Communication is over HTTP using Feign
Clients.
Technologies- Java 17, Spring Boot 3.4- Spring MVC (REST)- MySQL- Spring Data JPA (UUID IDs)- JUnit 5, MockMvc, Jacoco- Maven
Exposed REST Endpoints- GET /api/mechanics — returns all mechanics- GET /api/mechanics/{id} — 
returns mechanic by ID- POST /api/mechanics/assign — automatically assigns 
the first available mechanic- PUT /api/mechanics/{id}/availability — updates mechanic availability
Database Model
Mechanic entity:- UUID id- String name- String specialization- boolean available
Data Initialization
On startup (except when running tests), the service loads:- John Peterson – Engine Specialist- George Dimitrov 
– Suspension Specialist- Glen Gregory – Electronics Specialist
Testing
Includes:- Unit tests- Integration tests- API tests (MockMvc)- Exception handler tests- Jacoco 80%+ minimum coverage
Error Handling
Custom and built-in exceptions:- NoAvailableMechanicException- IllegalArgumentException- Generic Exception
Running the Microservice
1. Configure MySQL:
   URL: jdbc:mysql://localhost:3306/mechanic_service
   User: root
   Password: 121314
2. Start:
   mvn spring-boot:run
   Port: 8081
   Integration With Main Application
   The Main Application consumes:
- GET /api/mechanics- POST /api/mechanics/assign- PUT /api/mechanics/{id}/availability
  Project Structure
  MechanicAssignmentService/
  config/
  controller/
  model/
  repository/
  service/
  exception/
  test/