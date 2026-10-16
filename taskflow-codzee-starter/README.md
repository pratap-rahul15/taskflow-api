# TaskFlow API

A small Java 21 + Spring Boot REST API created as a sandbox for evaluating AI code-review tools.

## Requirements

- JDK 21
- Maven 3.9+ (or use your IDE's Maven integration)

## Run locally

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## Endpoints

| Method | Path | Purpose |
|---|---|---|
| `GET` | `/api/tasks` | List tasks |
| `GET` | `/api/tasks/{id}` | Get one task |
| `POST` | `/api/tasks` | Create a task |
| `PATCH` | `/api/tasks/{id}/complete` | Mark a task complete |

Example:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Review pull requests","description":"Evaluate code review comments"}'
```

Tasks are stored in memory and reset when the application restarts. This is intentional to keep the project easy to run without external services.

## Assessment workflow

Keep the default branch stable. For each planned change, create a new branch from the default branch, implement the change, run tests, push the branch, and open a pull request. Wait for Codzee's review before merging so that each review remains easy to inspect.

Suggested PRs (adapt these to the actual code and review findings; don't claim Codzee found something unless it did):

1. **Input validation and API errors** — reject blank/oversized titles and return consistent client errors.
2. **Thread-safe storage and update behavior** — make task creation/update behavior safe under concurrent requests and document the chosen semantics.
3. **Automated tests and list behavior** — add controller/service tests and, if useful, bounded pagination for task listing.

## Notes

This is a learning/assessment project, not a production-ready service. It has no authentication, durable database, rate limiting, or production deployment configuration. Do not use it to store sensitive data.
