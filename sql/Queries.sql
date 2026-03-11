-- Listar todos lo clientes

select * from clientes;

-- Ver todas las citas con la información del cliente

select c.id_cita, c.fecha, c.hora, cl.nombre, cl.telefono
from citas c 
inner join clientes cl 
on c.id_cliente = cl.id_cliente;

-- Ver todas las citas con cliente, servicio y empleado asignado

select c.id_cita, c.fecha, c.hora, cl.nombre as cliente, s.nombre as servicio, e.nombre as especialista
from citas c 
inner join clientes cl on c.id_cliente = cl.id_cliente
inner join servicios s on c.id_servicio = s.id_servicio
inner join empleados e on c.id_empleado = e.id_empleado;

-- Ordenar estas citas por hora

select c.id_cita, c.fecha, c.hora, cl.nombre as cliente, s.nombre as servicio, e.nombre as especialista
from citas c 
inner join clientes cl on c.id_cliente = cl.id_cliente
inner join servicios s on c.id_servicio = s.id_servicio
inner join empleados e on c.id_empleado = e.id_empleado
order by hora;

-- Ver las citas de una hora en concreto

select *
from citas
where hora = '10:00:00';

-- Ver qué empleado realiza un servicio concreto

select e.nombre
from servicio_empleado se 
inner join empleados e on se.id_empleado = e.id_empleado
inner join servicios s on s.id_servicio = se.id_servicio
where s.nombre like '%_ift%';

-- Ver cuantas citas tiene cada empleado

select e.nombre,
count(c.id_cita) as total_citas
from empleados e 
left join citas c on e.id_empleado = c.id_empleado
group by e.nombre;

-- Ver el precio del servicio de cada cita

select c.id_cita, s.nombre as servicio, s.precio
from citas c 
inner join servicios s on c.id_servicio = s.id_servicio;