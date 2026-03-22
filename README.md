🏢 Sistema de Gestión de Bienes - SENA

Este proyecto es una aplicación web desarrollada con Spring Boot que permite gestionar el inventario de bienes, instructores, centros de formación y regionales dentro de un entorno educativo como el SENA.

El sistema implementa una arquitectura basada en REST, permitiendo la comunicación entre cliente y servidor mediante peticiones HTTP, facilitando la gestión de información a través de herramientas como Postman.

🚀 Funcionalidades principales

- 📌 Registro, consulta, actualización y eliminación de bienes
- 👨‍🏫 Gestión de instructores
- 🏫 Administración de centros de formación
- 🌎 Manejo de regionales
- 🔎 Búsqueda de bienes por placa, consecutivo o instructor

🧠 Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MariaDB
- Maven
- Postman

🧩 Arquitectura

El proyecto está estructurado en capas:

- Controller → Manejo de peticiones HTTP
- Service → Lógica de negocio
- Repository → Acceso a datos
- DTO → Transferencia de datos optimizada

🔐 Buenas prácticas implementadas

- Uso de DTO para evitar recursividad infinita
- Manejo de códigos HTTP (200, 201, 404, 204)
- Separación de responsabilidades
- Manejo de excepciones

🧪 Pruebas

Las pruebas de los endpoints REST se realizaron utilizando Postman, validando las operaciones CRUD y los códigos de respuesta HTTP.

---

💡 Este proyecto fue desarrollado como parte del proceso de formación en el SENA, enfocado en el aprendizaje de desarrollo backend con Spring Boot.
