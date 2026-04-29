# Aplicación de Gestión — Belladonna Estética

## Módulo: Programación (0485) + MPO Ampliación de Programación

---

## Qué es

Aplicación de gestión por consola desarrollada en Java que permite administrar el día a día de Belladonna Estética. Se conecta a la base de datos MySQL del centro mediante JDBC y permite realizar operaciones CRUD sobre las principales entidades del negocio.

## Qué gestiona

- **Clientes** — Alta, consulta, modificación y eliminación de clientes del centro.
- **Servicios** — Gestión del catálogo de tratamientos (nombre, precio, duración).
- **Empleados** — Gestión del equipo profesional y sus especialidades.
- **Citas** — Reserva y gestión de citas vinculando cliente, servicio, empleado, fecha y hora.


## Mejora MPO: Consultas avanzadas en la gestión de citas

Como mejora para el módulo de Fundamentos de Programación, se ha implementado una funcionalidad adicional en la gestión de citas: **ver las citas programadas para el día de hoy**.

### Qué hace

Dentro del menú de gestión de citas se ha añadido la opción "Ver citas de hoy", que consulta la base de datos filtrando por la fecha actual del sistema (`LocalDate.now()`) y muestra únicamente las citas programadas para ese día.

### Cómo se ha implementado

En lugar de crear un método nuevo independiente, se ha reutilizado el método `obtenerCitasDetalladas()` del `CitaDAO` añadiéndole un parámetro opcional de tipo `LocalDate`:

- Si se le pasa `null`, devuelve todas las citas (comportamiento original).
- Si se le pasa una fecha, añade un `WHERE` a la query para filtrar por ese día.

Esto evita duplicar la query completa (que incluye tres JOINs con las tablas de clientes, servicios y empleados) y mantiene un único punto de mantenimiento.

### Por qué es una mejora

- **Reutilización de código**: un solo método sirve para dos funcionalidades distintas.
- **Consulta con JOINs**: las citas se muestran con los nombres del cliente, servicio y empleado en vez de solo los IDs, lo que aporta legibilidad real para el usuario.
- **Valor para el negocio**: en un centro de estética real, lo primero que necesitas al abrir por la mañana es saber qué citas hay hoy.

