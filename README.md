# Hospital-Management-System
A comprehensive Hospital Management System built using Java, Spring Boot, Hibernate/JPA, and PostgreSQL, designed to streamline hospital operations including patient management, doctor management, and department workflows. The system provides RESTful APIs for seamless interaction between patients, doctors, and administrators, with modular, scalable, and maintainable architecture.

# Features
## Patient Management
Register new patients under general queue or specific doctor.
Update patient appointment details (change doctor or department).
View appointment and doctor assignment details.
Cancel appointments with automatic slot management.

## Doctor Management
Register new doctors and assign them to departments.
Update doctor profiles and department assignments.
View all doctors by department.
Delete doctor profiles with automatic reassignment of patients to other doctors.

## System Architecture
One-to-Many relationship: A doctor can have multiple patients.
Many-to-One relationship: Each patient is assigned to one doctor.
Service-oriented architecture (Controller → Service → Repository) for modularity and maintainability.
RESTful APIs exposed on embedded Tomcat (port 8090).

## Technologies Used
Programming Language: Java

Frameworks & Libraries: Spring Boot, Hibernate, JPA

Database: PostgreSQL

Tools: Postman, IntelliJ IDEA, Visual Studio

Deployment: Embedded Tomcat

Other Concepts: CRUD operations, JPA relationships, RESTful API development, appointment workflow management
