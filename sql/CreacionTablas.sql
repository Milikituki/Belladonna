create table clientes (
    id_cliente int AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(50) not null,
    telefono varchar(50) not null,
    email varchar(100)
);

create table servicios (
    id_servicio int AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(50) not null,
    precio decimal(3,2),
    duracion INT
);

create table empleados (
    id_empleado int AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(50) not null,
    especialidad varchar(50)
);

create table cita (
    id_cita int AUTO_INCREMENT PRIMARY KEY,
    fecha date not null,
    hora time not null,
    
    id_cliente int,
    id_servicio int,
    id_empleado int,
    
    foreign key (id_cliente) references clientes(id_cliente),
    foreign key (id_servicio) references servicios(id_servicio),
    foreign key (id_empleado) references empleados(id_empleado)
    
    on delete restrict
    on update cascade
);

create table servicio_empleado (
    id_empleado int,
    id_servicio int,
    
    primary key (id_empleado, id_servicio),
    foreign key (id_empleado) references empleados(id_empleado),
    foreign key (id_servicio) references servicios(id_servicio)
    
    on delete RESTRICT
    on update CASCADE
);