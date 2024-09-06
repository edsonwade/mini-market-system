# 🛒 Mini Market Shop System

Welcome to the **Mini Market Shop System**! This project is a demo MVP (Minimum Viable Product) for a simple e-commerce application where users can add items to a cart and perform basic CRUD operations. The project is divided into a **Java Spring Boot backend** and a **React frontend** styled with **Tailwind CSS**.

## 🚀 Tech Stack

### Backend:
- **Java 17**
- **Spring Boot 3.3.1**
- **PostgreSQL** for database
- **Flyway** for database migrations
- **Spring Data JPA** for ORM
- **Spring Validation** for input validation
- **H2** (for in-memory testing)
- **Actuator** for monitoring the application

### Frontend:
- **React** (JavaScript framework)
- **Tailwind CSS** for styling
- **Axios** for API communication
- **React Router** for routing between pages

## ⚙️ Features

- **Cart Management**: Create, read, update, and delete carts.
- **Item Management**: Add items to carts, view item details, update, and remove items.
- **Full CRUD Functionality**: Both Cart and Item entities have complete CRUD operations from backend to frontend.
- **API-Driven Architecture**: The frontend communicates with the backend via RESTful API endpoints.

## 🛠️ Setup

### Backend Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/mini-market-shop.git
    cd mini-market-shop/backend
    ```

2. **Set up PostgreSQL**:
   - Make sure PostgreSQL is installed and running.
   - Create a database for the project:
     ```sql
     CREATE DATABASE mini_market;
     ```

3. **Configure application properties**:
   Update the `src/main/resources/application.yml` file with your PostgreSQL credentials:
   ```yml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/mini_market
       username: your_username
       password: your_password
     jpa:
       hibernate:
         ddl-auto: update
       properties:
         hibernate:
           dialect: org.hibernate.dialect.PostgreSQLDialect
    ```
