# Digital Library Management System

A full-stack Digital Library Management System built with Spring Boot MVC, Spring Data JPA/Hibernate, MySQL, JWT security, and a React frontend.

## Features

- Admin and user role-based access
- JWT authentication with BCrypt password hashing
- Book catalogue CRUD, search, pagination, sorting, and filtering
- Book issue and return flow with automatic due dates
- Fine calculation at Rs. 5 per overdue day
- Reservations for unavailable books
- Contact query management
- Admin dashboard metrics
- Layered MVC backend structure
- Responsive React frontend structure
- SQL schema, UML PlantUML diagrams, SRS, and deployment guide

## Project Structure

```text
backend/
  src/main/java/com/library/
    config/
    controller/
    dto/
    entity/
    exception/
    repository/
    service/
    util/
  src/main/resources/
frontend/
database/
docs/
.github/workflows/
```

## Backend Setup

1. Create a MySQL database:

```sql
CREATE DATABASE digital_library;
```

2. Configure credentials in `backend/src/main/resources/application.yml` or environment variables.

3. Run the backend:

```bash
cd backend
mvn spring-boot:run
```

Backend runs on `http://localhost:8080`.

## Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

## Important APIs

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/books`
- `GET /api/books/search`
- `POST /api/admin/books`
- `PUT /api/admin/books/{id}`
- `DELETE /api/admin/books/{id}`
- `POST /api/issues`
- `PUT /api/issues/return/{issueId}`
- `POST /api/reservations`
- `GET /api/fines/me`
- `POST /api/contact`
- `GET /api/admin/dashboard`

## Default Roles

Users register as `USER`. Create an admin directly in the database or extend the seed process for your environment.

## Documentation

- SQL schema: `database/schema.sql`
- SRS: `docs/SRS.md`
- API docs: `docs/API.md`
- UML diagrams: `docs/uml/*.puml`
- Deployment guide: `docs/DEPLOYMENT.md`

