﻿# ✅ Tasks System

This project is a desktop application developed in **Java** that leverages **Spring Boot**, **MySQL**, and **JavaFX** to manage tasks efficiently. The application provides a graphical user interface for creating, reading, updating, and deleting (CRUD) tasks stored in a database, making task management simple and intuitive.

![Project Preview](images/preview.png)

## ✨ Features

- **List Tasks**: Displays all tasks stored in the database.
- **Add Task**: Allows users to create new tasks with a name, manager, and status.
- **Edit Task**: Enables updating the details of an existing task.
- **Delete Task**: Allows users to remove a task from the database.
- **Graphical User Interface**: User-friendly interface built with **JavaFX** for enhanced usability.

## ⚙️ Installation and Configuration

### Prerequisites

- **Java 11** or higher installed.
- **MySQL** database server installed and running.

### Database Configuration

1. Create a MySQL database named `tasks_db`:

    ```sql
    CREATE DATABASE tasks_db;
    ```

2. Create a `tasks` table with the following structure:

    ```sql
    CREATE TABLE tasks (
        taskId INT AUTO_INCREMENT PRIMARY KEY,
        taskName VARCHAR(255) NOT NULL,
        taskManager VARCHAR(255) NOT NULL,
        status VARCHAR(50) NOT NULL
    );
    ```

### Project Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/javier04s/Tasks-System.git
    cd Tasks-System
    ```

2. Update the database connection settings in the `src/main/resources/application.properties` file:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/tasks_db?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=admin
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Additional configurations
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.main.web-application-type=none
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## 🖥️ Usage

- Launch the application.
- Use the GUI to perform the following operations:
    - **Add a Task**: Fill in the details and click "Add".
    - **Edit a Task**: Select a task, make changes, and click "Edit".
    - **Delete a Task**: Select a task and click "Delete".
    - **View Tasks**: All tasks will be displayed in a table.

## 🛠️ Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Backend framework for managing business logic and database connections.
- **MySQL**: Database to store task data.
- **JavaFX**: Frontend framework for the graphical user interface.

## 📜 License

This project is licensed under the MIT License.
