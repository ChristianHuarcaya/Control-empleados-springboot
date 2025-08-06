# 🧑‍💼 Sistema de Control de Empleados

Proyecto desarrollado con Spring Boot, Thymeleaf y Spring Security que permite administrar empleados con funciones completas como CRUD, paginación, generación de reportes en PDF y Excel, y autenticación basada en roles (ADMIN/USER).

## 🚀 Funcionalidades

✅ Registro, edición y eliminación de empleados  
✅ Búsqueda por nombre o puesto  
✅ Visualización de detalles por empleado  
✅ Reportes en formato PDF y Excel  
✅ Paginación de resultados  
✅ Login con Spring Security  
✅ Acceso restringido según roles (`ADMIN`, `USER`)

---

## 🧰 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- Thymeleaf  
- Bootstrap  
- MySQL  
- Apache POI (para exportar Excel)  
- iText PDF (para exportar PDF)  
- Maven  

---

## 🔐 Accesos por rol

| Usuario      | Contraseña  | Rol    |
|--------------|-------------|--------|
| `admin`      | `admin123`  | ADMIN  |
| `usuario`    | `user123`   | USER   |

---

## 📂 Estructura del proyecto

- `entity`: Entidades de la base de datos (`Empleado`, `Puesto`, etc.)
- `repository`: Repositorios JPA
- `service`: Lógica de negocio
- `controller`: Controladores para manejar las vistas y peticiones
- `security`: Configuración de Spring Security
- `templates`: Vistas Thymeleaf
- `static`: Archivos CSS y JS

---

## 🛠️ Cómo ejecutar localmente

1. Clona el repositorio:
   ```bash
   git clone https://github.com/ChristianHuarcaya/control-empleados-springboot.git

## 👨‍💻 Autor

**Cristian Huarcaya Pumahualcca**  
Desarrollador Backend en Java  
[LinkedIn](https://www.linkedin.com/in/christian-huarcaya-pumahualcca) | [GitHub](https://github.com/ChristianHuarcaya)





