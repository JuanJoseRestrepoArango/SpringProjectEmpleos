# BDScott App - Gestión de Empleados y Departamentos

Sistema de gestión empresarial desarrollado con **Spring Boot 4**, enfocado en el acceso a datos y seguridad basada en roles. El proyecto implementa operaciones CRUD completas y un sistema de autenticación robusto.

## 🚀 Características

* **Gestión de Empleados**: CRUD completo para la administración de personal.
* **Gestión de Departamentos**: Control total sobre las áreas de la empresa.
* **Seguridad Integral**: 
    * Registro de nuevos usuarios y login seguro.
    * Vistas dinámicas controladas por roles (ADMIN, SUPERVISOR, USUARIO).
    * Protección de rutas a nivel de método y URL.
* **Interfaz Moderna**: Diseño responsivo utilizando Bootstrap 5 y FontAwesome.

## 🛠️ Tecnologías utilizadas

* **Java 17**
* **Spring Boot 4.0.1**
* **Spring Data JPA**: Para la persistencia con MySQL.
* **Spring Security**: Autenticación y Autorización (RBAC).
* **Thymeleaf**: Motor de plantillas con integración de Security Tags.
* **MySQL**: Base de datos relacional (Esquema SCOTT).
* **Bootstrap 5**: Framework de estilos CSS.

## 📋 Requisitos Previos

1.  Java 17 instalado.
2.  MySQL Server funcionando.
3.  IDE (Recomendado: Spring Tool Suite o Eclipse con STS plugin).

## ⚙️ Configuración del Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/JuanJoseRestrepoArango/SpringProjectEmpleos.git](https://github.com/JuanJoseRestrepoArango/SpringProjectEmpleos.git)

```

2. **Configurar la base de datos:**
Asegúrate de tener las siguientes propiedades en tu `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_tu_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

```


3. **Ejecutar la aplicación:**
Desde STS: `Right Click en Proyecto -> Run As -> Spring Boot App` o vía Maven:
```bash
mvn spring-boot:run

```



## 🔐 Roles y Acceso

| Rol | Permisos |
| --- | --- |
| **ADMINISTRADOR** | Acceso total (Usuarios, Departamentos, Empleados) |
| **SUPERVISOR** | Gestión de Departamentos y Empleados |
| **USUARIO** | Registro y visualización limitada |

