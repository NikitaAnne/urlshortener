# URL Shortener API

A backend application built using Spring Boot that generates short URLs and redirects users to the original links. The project also includes Redis caching, PostgreSQL persistence, expiration handling, Docker support, and Swagger API documentation.

This project was built to improve backend development skills beyond basic CRUD APIs by implementing concepts commonly used in real production systems.

---

# Features

* Generate short URLs
* Redirect to original URLs
* PostgreSQL database integration
* Redis caching for faster lookups
* URL expiration support
* Scheduled cleanup for expired URLs
* Global exception handling
* Swagger/OpenAPI documentation
* Dockerized setup using Docker Compose

---

# Tech Stack

* Java 21
* Spring Boot 3.5
* Spring Web
* Spring Data JPA
* PostgreSQL
* Redis
* Docker
* Docker Compose
* Swagger / OpenAPI
* Maven

---

# Project Structure

```text
src/main/java/com/nikki/urlshortener
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── scheduler
├── config
└── util
```

---

# API Endpoints

## Create Short URL

```http
POST /api/v1/url
```

### Request Body

```json
{
  "originalUrl": "https://google.com"
}
```

### Response

```json
{
  "shortUrl": "http://localhost:8080/api/v1/url/a3728b"
}
```

---

## Redirect to Original URL

```http
GET /api/v1/url/{shortCode}
```

Example:

```http
GET /api/v1/url/a3728b
```

---

# Swagger Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Running the Project Locally

## Prerequisites

Make sure the following are installed:

* Java 21
* Maven
* Docker Desktop

---

# Run Using Docker

## Build and start containers

```bash
docker compose up --build
```

This starts:

* Spring Boot application
* PostgreSQL database
* Redis server

---

# Stop Containers

```bash
docker compose down
```

---

# Database

PostgreSQL is used to store:

* original URL
* generated short code
* creation timestamp
* expiration timestamp
* click count

---

# Redis Caching

Redis is used to reduce repeated database lookups during URL redirection.

Flow:

1. Check Redis cache
2. If not found, fetch from PostgreSQL
3. Store value in Redis
4. Return original URL

---

# Expiration Logic

Generated URLs expire after a configured duration.

A scheduled cleanup job removes expired entries periodically.

---

# What I Learned

This project helped me practice:

* REST API development
* Redis integration
* Docker basics
* Spring Boot architecture
* Exception handling
* Scheduler jobs
* PostgreSQL integration
* API documentation using Swagger

---

# Future Improvements

* User authentication
* Analytics dashboard
* Custom short URLs
* Rate limiting
* QR code generation
* Kubernetes deployment

---

# Author

Nikita Anne Jacob
