# Deployment Guide

## Docker

```bash
docker compose up --build
```

## Render or Railway

1. Create a MySQL database service.
2. Set environment variables:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
   - `JWT_SECRET`
3. Deploy `backend` as a Java Maven service.
4. Deploy `frontend` as a static Vite app with `VITE_API_URL`.

## AWS EC2 or VPS

1. Install Java 21, Maven, Node.js, and MySQL 8.
2. Build backend:

```bash
cd backend
mvn clean package
java -jar target/digital-library-1.0.0.jar
```

3. Build frontend:

```bash
cd frontend
npm install
npm run build
```

4. Serve `frontend/dist` with Nginx and reverse proxy `/api` to Spring Boot.
