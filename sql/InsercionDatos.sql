
-- El precio se creó como decimal(3,2), que sólo admite valores de hasta 9.99 y se corrigió para admitir 
-- valores de hasta 999.99
alter table servicios modify precio decimal (5,2);

insert into servicios (nombre, precio, duracion) VALUES 
('Manicura semipermanente', 26.00, 60), 
('Lifting de pestañas', 35.00, 90), 
('Depilación de cejas', 15.00, 20), 
('Pedicura semipermanente', 20.00,60);

insert into empleados (nombre, especialidad) VALUES
('Davinia Prados', 'Manicura y pedicura'),
('Mercedes Mendoza', 'Depilación cera e hilo'),
('Paquita Navarro', 'Lifting y microblading');

insert into clientes (nombre, telefono, email) VALUES
('Miriam Fernandez', '651475359', 'mfdez@gmail.com'),
('Catalina Torrandell', '665257339', 'ctorr@gmail.com'),
('Yerling Murillo', '685288196', 'ymur@gmail.com'),
('Laura Bibiloni', '971867379', 'lbibi@gmail.com'),
('Aina Amengual', '971865715', 'ameng@gmail.com');


-- Durante el desarrollo, los empleados se insertaron varias veces debido a errores en las especialidades,
-- que provocó que AUTOINCREMENT asignara ids más altos, porque lo que se optó por corregirlo para que la 
-- base de datos quede "limpia"
insert into servicio_empleado (id_empleado, id_servicio) VALUES
(1,1),
(1,4),
(1,3),
(2,3),
(2,2),
(3,1),
(3,4),
(3,2);

-- La tabla se creó inicialmente como "cita", en singular, y se ha renombrado para mantener la coherencia
-- con el resto de tablas
alter table cita rename to citas;

insert into citas (fecha, hora, id_cliente, id_servicio, id_empleado) values 
('2026-03-20', '10:00:00', 1, 1, 1),
('2026-03-20', '11:00:00', 2, 1, 3),
('2026-03-20', '10:00:00', 3, 2, 2),
('2026-03-20', '12:00:00', 4, 3, 3),
('2026-03-20', '12:00:00', 5, 4, 1);




