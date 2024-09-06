# 🛒 Mini Market Shop System

Welcome to the **Mini Market Shop System**! This project is a demo MVP (Minimum Viable Product) for a simple e-commerce
application where users can add items to a cart and perform basic CRUD operations. The project is divided into a **Java
Spring Boot backend** and a **React frontend** styled with **Tailwind CSS**.

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

## Project Structure 📂

- **Backend:**

````tree
backend/
├── src/main/java/com/marketshop
│   ├── controller
│   │   └── CartController.java
│   │   └── ItemController.java
│   ├── entity
│   │   └── Cart.java
│   │   └── Item.java
│   ├── repository
│   │   └── CartRepository.java
│   │   └── ItemRepository.java
│   ├── service
│   │   └── CartService.java
│   │   └── ItemService.java
│   └── MiniMarketShopApplication.java
└── resources
    ├── db/migration
    │   └── V1__Create_Cart_Item_Tables.sql
    └── application.yml
````

- **Frontend:**

```tree
frontend/
├── src/
│   ├── components/
│   │   ├── CartForm.jsx
│   │   ├── CartList.jsx
│   │   ├── ItemForm.jsx
│   │   └── ItemList.jsx
│   ├── pages/
│   │   ├── HomePage.jsx
│   │   ├── CartPage.jsx
│   │   └── ItemPage.jsx
│   ├── api/
│   │   ├── cartApi.js
│   │   └── itemApi.js
│   └── App.jsx
└── index.jsx

```

## Database Migrations 🗂️

The backend uses Flyway for managing database schema.
The migration script is located in **src/main/resources/db/migration.**

Example migration file: **V1__Create_Cart_Item_Tables.sql**

```sql
CREATE TABLE IF NOT EXISTS tb_carts
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_items
(
    id            BIGINT PRIMARY KEY,
    serial_number VARCHAR(20),
    cart_id       BIGINT,
    CONSTRAINT fk_cart
        FOREIGN KEY (cart_id)
            REFERENCES tb_carts (id)
            ON DELETE CASCADE
);

```

## Testing

- The backend includes basic unit tests to ensure the service and controller layers function correctly.
- Run the tests:

   ```bash
    ./mvnw test
   ```

## Frontend Testing

Ensure that the frontend performs basic CRUD operations through your browser by visiting the application.

🌐 **API Endpoints**

### Cart Endpoints:

- `GET /api/carts` - Get all carts
- `GET /api/carts/{id}` - Get cart by ID
- `POST /api/carts` - Create new cart
- `PUT /api/carts/{id}` - Update existing cart
- `DELETE /api/carts/{id}` - Delete cart by ID

### Item Endpoints:

- `POST /api/items` - Create new item (within a cart)
- `DELETE /api/items/{id}` - Delete item by ID

💻 **Frontend Components**

- **CartList**: Displays a list of carts.
- **ItemList**: Displays a list of items within a selected cart.
- **CartForm**: Form to create or edit a cart.
- **ItemForm**: Form to add a new item to a cart.

🎨 **Tailwind CSS Styling**

The frontend is styled using Tailwind CSS for a responsive and modern UI. Basic forms, buttons, and layout components
are included, but you can extend and customize the styles as needed.

📜 **License**

This project is licensed under the MIT License. See the LICENSE file for details.

🤝 **Contributing**

Feel free to open issues or submit pull requests for improvements. Contributions are always welcome!

👏 **Acknowledgments**

Special thanks to:

- The Spring Boot team for the awesome framework.
- The React and Tailwind CSS communities for the great tools.
