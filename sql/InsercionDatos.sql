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
('Aina Amengual', '971865715', 'ameng@gamil.com');

insert into servicio_empleado (id_empleado, id_servicio) VALUES
(7,1),
(7,4),
(7,3),
(8,3),
(8,2),
(9,1),
(9,4),
(9,2);

insert into cita (fecha, hora, id_cliente, id_servicio, id_empleado) values 
('2026-03-20', '10:00:00', 1, 1, 7),
('2026-03-20', '11:00:00', 2, 1, 9),
('2026-03-20', '10:00:00', 3, 2, 8),
('2026-03-20', '12:00:00', 4, 3, 9),
('2026-03-20', '12:00:00', 5, 4, 7);


alter table cita rename to citas;


