# DevJobs – Job Platform using Hexagonal Architecture + Rich DDD

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)](https://spring.io/projects/spring-boot)
[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal%20%7C%20Rich%20DDD-orange)](#)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=dev-jobs-backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dev-jobs-backend)
[![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)](#)

---

## 📌 Overview

**DevJobs** is a job platform built with a strong focus on **clean and scalable architecture**, using:

- 🌱 **Rich Domain-Driven Design (DDD)** to encapsulate business logic
- 🧱 **Hexagonal Architecture** to ensure low coupling and high testability
- 🔁 **REST** as the entry adapter (initial delivery)
- 🧪 A project prepared to evolve with tests, GraphQL, and AI integration

---

## 🧠 Technologies

- Java 17
- Spring Boot 3.x
- Maven
- RESTful API
- Hexagonal Architecture
- Domain-Driven Design (DDD)
- In-Memory Repository (temporary)

---

## 🚀 How to Run the Project

```bash
git clone https://github.com/SEU_USUARIO/dev-jobs-backend.git
cd dev-jobs-backend
./mvnw spring-boot:run
```

By default, the application runs on port `8080`. You can test it with:

```bash
curl -X POST "http://localhost:8080/vagas?titulo=Dev%20Java&descricao=Vaga%20Spring&localizacao=Remoto"
```

---

## 🔭 Next Steps

- [ ] Swagger/OpenAPI documentation
- [ ] DTO layer + validation
- [ ] Unit and integration tests
- [ ] Integration with a real database (JPA/PostgreSQL)
- [ ] GraphQL as an additional adapter
- [ ] AI module for smart suggestions

---

## 📚 Documentation

Looking for more details about the architecture, technical decisions, or MVP planning?

👉 Check out the [Project Wiki](../../wiki) for a full breakdown.


---

## 👨🏽‍💻 Author

**Zuamir Oliveira**  
[GitHub Profile](https://github.com/zuamirgoliveira)

> "I started something big that's not over yet. The deadline may have passed, but the development continues."

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
