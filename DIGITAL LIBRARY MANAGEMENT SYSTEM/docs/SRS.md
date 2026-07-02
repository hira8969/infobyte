# Software Requirements Specification

## Abstract

The Digital Library Management System automates book cataloguing, issue/return tracking, reservations, fines, and user query handling.

## Objectives

- Reduce manual library operations.
- Provide secure role-based access.
- Maintain accurate inventory and fine records.
- Offer a responsive web interface and REST APIs.

## Scope

The system supports administrators and registered users. Admins manage books, users, fines, reservations, and queries. Users browse/search books, issue available books, reserve unavailable books, view fines, and contact the library.

## Functional Requirements

- User registration and JWT login.
- Admin book CRUD.
- Book search with pagination and sorting.
- Book issuing with 14-day default due date.
- Book returning with overdue fine calculation.
- Reservation creation when a book is unavailable.
- Contact query submission and admin viewing.
- Admin dashboard analytics.

## Non-Functional Requirements

- Secure password storage with BCrypt.
- Stateless JWT authentication.
- Layered MVC architecture.
- Validation with Jakarta Validation.
- Persistence through Spring Data JPA and Hibernate.
- MySQL 8 compatibility.

## Architecture

Controller receives HTTP requests, Service handles business rules, Repository abstracts persistence, and Entity maps database tables.

```text
React UI -> REST Controller -> Service -> Repository -> Hibernate/JPA -> MySQL
```

## Database Design

Core tables: `users`, `roles`, `user_roles`, `books`, `issue_records`, `fines`, `reservations`, `contact_queries`, `audit_logs`.

## Security Design

Public endpoints include authentication, contact submission, and book browsing. Authenticated users can issue, return, reserve, and view their own records. Admin endpoints require `ADMIN`.

## Testing Strategy

- Unit tests for fine calculation and service validations.
- Controller tests with MockMvc for API behavior.
- Repository tests with an isolated test database for JPA queries.
- Integration tests for complete issue/return flows.

## Future Enhancements

- QR/barcode tracking.
- Email and SMS notifications.
- AI recommendations.
- Mobile application.
- Multi-library support.
- PDF/Excel reports.
- Cloud-native deployment.

## Conclusion

This project provides a clean, extensible foundation for a production-style digital library platform.
