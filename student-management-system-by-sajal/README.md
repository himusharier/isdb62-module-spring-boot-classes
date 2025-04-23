# Student Management System

A RESTful API for managing student information using Spring Boot.

## Features

- CRUD operations for student entities
- Validation of input data
- Error handling with appropriate HTTP status codes
- In-memory H2 database for development
- Lombok for reducing boilerplate code
- Proper exception handling and logging

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok
- SLF4J / Logback for logging

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the following command:

```bash
mvn spring-boot:run
```

The application will start on port 8080.

### H2 Console

The H2 console is enabled and can be accessed at:

```
http://localhost:8080/h2-console
```

Connection details:
- JDBC URL: jdbc:h2:mem:studentdb
- Username: sa
- Password: password

## API Endpoints

### Student Controller

| Method | URL                   | Description                 | Status Code |
|--------|------------------------|------------------------------|------------|
| POST   | /api/students          | Create a new student         | 201        |
| GET    | /api/students          | Get all students             | 200        |
| GET    | /api/students/{id}     | Get student by ID            | 200        |
| PUT    | /api/students/{id}     | Update student               | 200        |
| DELETE | /api/students/{id}     | Delete student               | 204        |

## Request/Response Examples

### Create Student

**Request:**
```json
POST /api/students
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "2000-01-15"
}
```

**Response:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "2000-01-15",
  "enrollmentDate": "2025-04-17T18:30:00"
}
```

### Get All Students

**Request:**
```
GET /api/students
```

**Response:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "dateOfBirth": "2000-01-15",
    "enrollmentDate": "2025-04-17T18:30:00"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "dateOfBirth": "2001-05-20",
    "enrollmentDate": "2025-04-17

