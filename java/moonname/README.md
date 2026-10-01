# MoonName

A Spring MVC application for booking a name inscription on a fictional lunar mission. Includes rockets, missions, stones, carts, orders and CLIENT, ASTRONAUTE and ADMIN roles. No real payments.

Java 17+ · Spring Boot · Thymeleaf · Spring Security · JPA · PostgreSQL

## Run

1. Create a local PostgreSQL database named `moonname`, using `postgres` on port `5434`.
2. Set `DB_PASSWORD` in your environment.
3. From this folder, run `sh mvnw spring-boot:run` or `.\mvnw.cmd spring-boot:run` on Windows.
4. Open http://localhost:8080.

Registration creates a CLIENT account. Admin and astronaut accounts require local setup. The development configuration updates the database schema automatically.

## Tests

Run `sh mvnw test` or `.\mvnw.cmd test` on Windows. Tests use isolated H2 storage; PostgreSQL locking still needs separate validation.

Work in progress: database migrations, demo data and role provisioning. See [development notes](docs/CONSOLIDATION.md).
