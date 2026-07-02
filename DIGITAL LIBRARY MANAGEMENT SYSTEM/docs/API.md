# API Documentation

Base URL: `http://localhost:8080/api`

## Authentication

### Register

`POST /auth/register`

```json
{
  "fullName": "Asha Sharma",
  "email": "asha@example.com",
  "username": "asha",
  "password": "Password123",
  "phoneNumber": "9876543210"
}
```

### Login

`POST /auth/login`

```json
{
  "username": "asha",
  "password": "Password123"
}
```

Use returned token as:

```text
Authorization: Bearer <token>
```

## Books

- `GET /books?page=0&size=10&sort=title,asc`
- `GET /books/search?q=java`
- `GET /books/{id}`
- `POST /admin/books`
- `PUT /admin/books/{id}`
- `DELETE /admin/books/{id}`

## Issues

- `POST /issues` with `{ "bookId": 1 }`
- `GET /issues/me`
- `PUT /issues/return/{issueId}`

## Fines

- `GET /fines/me`
- `GET /fines`
- `PATCH /fines/{id}/paid`

## Reservations

- `POST /reservations` with `{ "bookId": 1 }`
- `GET /reservations/me`
- `GET /reservations`

## Contact Queries

- `POST /contact`
- `GET /admin/queries`

## Admin

- `GET /admin/dashboard`
- `GET /admin/users`
- `PATCH /admin/users/{id}/active?active=false`
