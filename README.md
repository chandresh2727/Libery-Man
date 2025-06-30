# 📚 Library Management System (Spring Boot)

A RESTful backend application for managing a library's book inventory, built using **Spring Boot**, **MySQL**

---

## 🚀 Features

- ✅ Add, update, delete, and view books (CRUD)
- 🔍 Search books by title or author
- 📄 Pagination and filtering by author/year
- ❗ Prevent duplicate ISBNs with validation
- 🗑️ Soft delete (no permanent data loss)
- 🔔 Background notification when a book becomes available
- 🧼 Clean code with design patterns (Strategy, Service Layer)
- 📦 DTO-layer mapping (decouples API from entities)

---

## ⚙️ Tech Stack

| Layer      | Tech                           |
|------------|--------------------------------|
| Backend    | Java, Spring Boot (3.5.x)      |
| DB         | MySQL                          |
| ORM        | Spring Data JPA + Hibernate    |
| Build Tool | Maven                          |
| Design     | RESTful APIs, DTO, Strategy DP |
| Testing    | Postman / curl (manual)        |

---

## 📂 Project Structure

src/
├── controller/          # REST API endpoints
├── service/             # Business logic
├── repository/          # Data access layer (JPA)
├── dto/                 # Request/response payloads
├── model/               # JPA entity classes
└── notification/        # Notification strategy logic

git clone https://github.com/chandresh2727/Libery-Man.git
cd Libery-Man

CREATE DATABASE library;

