# Customer Inquiry API
A Spring Boot REST API for handling customer inquiries, made for a technical test for Metafocus.

## Requirements
- Java 21. The Maven wrapper is included, so Maven does not need to be installed.

## How to run
- **IntelliJ:** open the project and run `InquiriesApplication`.
- **Terminal:** `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).
- The app runs locally on http://localhost:8080

## API
| Method | Path                       | Body                              | Success | Errors   |
|--------|----------------------------|-----------------------------------|---------|----------|
| POST   | /api/inquiries             | {"name": "...", "message": "..."} | 201     | 400      |
| GET    | /api/inquiries             | -                                 | 200     | -        |
| GET    | /api/inquiries/{id}        | -                                 | 200     | 404      |
| PATCH  | /api/inquiries/{id}/status | {"status": "IN_PROGRESS"}         | 200     | 400, 404 |

New inquiries always start with status `NEW`. Allowed statuses: `NEW`, `IN_PROGRESS`, `CLOSED`.

## Examples
```bash
# Create
curl -i -X POST localhost:8080/api/inquiries -H "Content-Type: application/json" -d '{"name":"Simon","message":"Hello"}'

# List all
curl -i localhost:8080/api/inquiries

# Get one
curl -i localhost:8080/api/inquiries/1

# Update status
curl -i -X PATCH localhost:8080/api/inquiries/1/status -H "Content-Type: application/json" -d '{"status":"IN_PROGRESS"}'
```

Error examples:
- Unknown id → `404 {"error":"Inquiry with id 999 not found"}`
- Empty name → `400 {"name":"Name is required"}`
- Invalid status → `400 {"error":"Invalid request body. Status must be one of: NEW, IN_PROGRESS, CLOSED"}`

The examples use bash (Git Bash, Linux, macOS). In Windows PowerShell, use `curl.exe`.

## Design notes
- Layered design: controller (HTTP) → service (business rules) → repository (storage).
  The storage can easily be swapped for a database without changing the controller.
- DTOs for input, so clients can only send allowed fields (no id or status on creation).
- In-memory storage with `ConcurrentHashMap` + `AtomicLong`, so it is thread-safe when
  requests run on several threads.
- Validation with Bean Validation (`@NotBlank`, `@Size`, `@NotNull`) and one
  `@RestControllerAdvice` that turns exceptions into clear JSON errors.

## Assumptions
- Data is lost when the application restarts, since storage is in-memory.
- Any status can change to any other status, since no transition rules were defined.
- No authentication.

## Limitations
- No persistence (see assumptions).
- No pagination or filtering; `GET /api/inquiries` does not guarantee order.
- No automated tests yet.

## Improvements with more time
- A real database like PostgreSQL, using Spring Data JPA and Flyway migrations.
- Pagination and filtering by status (e.g. `GET /api/inquiries?status=NEW`).
- Status transition rules (e.g. a CLOSED inquiry cannot be reopened).
- MockMvc tests for all endpoints and error cases.
- Authentication, OpenAPI/Swagger docs, Docker and a CI pipeline.
