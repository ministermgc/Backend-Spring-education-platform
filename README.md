# CodeNest – Education platform

The application is an educational platform for web development.

## Project Description

The project is a backend for an educational platform designed for web development learning. The platform provides an intuitive and interactive way to learn diverse web development concepts.

## Technologies and Tools

This educational platform is implemented with:

- Spring Boot 3.2.5: The base framework to build stand-alone, production-grade Spring-based applications quickly.
- Java: It serves as the core programming language for the development.
- Spring Data JPA: It simplifies the development of creating a data access layer by reducing the amount of boilerplate code.
- Spring Security: It provides authentication and authorization to guard the application.
- io.jsonwebtoken: A Java library used to create and verify JSON Web Tokens (JWTs).
- Lombok: Dramatically reduces boilerplate code, increasing readability and decreasing the chance for errors.
- PostgreSQL: The data storage solution provides the scalability and performance needed to handle the data of users, courses, and learning results.
- Docker: Used for defining and running the application in a Docker Container.

## Functionalities

- User Registration and Authentication: This system has the functionality to allow new users to create a new account, using their desired login credentials. Existing users can easily access their personal account by entering their username and password.
- Personal User Account Management: Each user has the ability to view and alter their personal account details. They can make necessary updates to their personal information such as username, email, and password changes.
- Lesson Management: Administrators have the authority to fully manage available lessons. They can add new lessons, make necessary updates to existing lessons, or delete lessons when needed. Regular users have the ability to access these lessons, with the feature to retrieve any lesson specifically by its unique identifier.
- Dashboard and Lesson Access: Users are given a comprehensive overview of lessons available through a lesson feed. This feed provides a list of lessons in a paginated format for easy navigation. The feature also allows users to access all lessons available on the platform, providing a detailed list in a navigatable format.
- Task Management: Administrators can set up tasks related to each lesson. They have control over the task information, where they can add new tasks, update task details or delete tasks as needed. For regular users, they have the opportunity to not only retrieve tasks based on identifiers but also to make submissions of task solutions for grading. Post submissions, users will receive the correct solutions for their tasks.

## Beginning of work

To run the application you will need Java, Maven and Docker Compose.

### Build and run

1. Clone the repository:
```shell
git clone https://github.com/ministermgc/Backend-Spring-education-platform.git
```
2. Go to the project directory:
```shell
cd Backend-Spring-education-platform
```
3. The ``.env`` environment variables must be specified before running the application. The required variables are in the ``.env-example`` file
4. Launch the application:
```shell
make launch
```

## API endpoints

The following endpoints are available by http://localhost:8080/swagger-ui/index.html
