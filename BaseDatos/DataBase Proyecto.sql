-- Crear tabla IPS
CREATE TABLE IPS (
    nit VARCHAR2(20) CONSTRAINT PK_IPS PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    direccion VARCHAR2(200) NOT NULL,
    telefono VARCHAR2(50) NOT NULL
);

-- Crear tabla ServicioSalud
CREATE TABLE ServicioSalud (
    idServicio VARCHAR2(20) CONSTRAINT PK_ServicioSalud PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    tipoServicio VARCHAR2(50) NOT NULL,
    requiereOrden NUMBER(1) NOT NULL,
    descripcion VARCHAR2(300) NOT NULL
);

-- Crear tabla Medico
CREATE TABLE Medico (
    registroMedico VARCHAR2(50) CONSTRAINT PK_Medico PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    tipoDocumento VARCHAR2(50) NOT NULL,
    numDocumento NUMBER NOT NULL,
    especialidad VARCHAR2(100) NOT NULL
);

-- Crear tabla Afiliado
CREATE TABLE Afiliado (
    idAfiliado VARCHAR2(20) CONSTRAINT PK_Afiliado PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    tipoAfiliado VARCHAR2(50) NOT NULL,
    tipoDocumento VARCHAR2(50) NOT NULL,
    numDocumento NUMBER NOT NULL,
    direccion VARCHAR2(200) NOT NULL,
    telefono VARCHAR2(50) NOT NULL,
    idAfiliado1 VARCHAR2(20),
    CONSTRAINT FK_Afiliado_Afiliado FOREIGN KEY (idAfiliado1) REFERENCES Afiliado(idAfiliado)
);

-- Crear tabla OrdenServicio
CREATE TABLE OrdenServicio (
    idOrden VARCHAR2(20) CONSTRAINT PK_OrdenServicio PRIMARY KEY,
    fecha DATE NOT NULL,
    estadoOrden VARCHAR2(50) NOT NULL,
    tipoOrden VARCHAR2(50) NOT NULL,
    descripcion VARCHAR2(300) NOT NULL,
    registroMedico VARCHAR2(50) NOT NULL,
    idServicio VARCHAR2(20) NOT NULL,
    CONSTRAINT FK_OrdenServicio_Medico FOREIGN KEY (registroMedico) REFERENCES Medico(registroMedico),
    CONSTRAINT FK_OrdenServicio_ServicioSalud FOREIGN KEY (idServicio) REFERENCES ServicioSalud(idServicio)
);

-- Crear tabla Cita
CREATE TABLE Cita (
    idCita VARCHAR2(20) CONSTRAINT PK_Cita PRIMARY KEY,
    fecha DATE NOT NULL,
    estadoCita VARCHAR2(50) NOT NULL,
    idOrden VARCHAR2(20) NOT NULL,
    idAfiliado VARCHAR2(20) NOT NULL,
    registroMedico VARCHAR2(50) NOT NULL,
    CONSTRAINT FK_Cita_OrdenServicio FOREIGN KEY (idOrden) REFERENCES OrdenServicio(idOrden),
    CONSTRAINT FK_Cita_Afiliado FOREIGN KEY (idAfiliado) REFERENCES Afiliado(idAfiliado),
    CONSTRAINT FK_Cita_Medico FOREIGN KEY (registroMedico) REFERENCES Medico(registroMedico)
);

-- Crear tabla Intermedia "Trabaja" (Relación IPS - Medico)
CREATE TABLE Trabaja (
    nit VARCHAR2(20) NOT NULL,
    registroMedico VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_Trabaja PRIMARY KEY (nit, registroMedico),
    CONSTRAINT FK_Trabaja_IPS FOREIGN KEY (nit) REFERENCES IPS(nit),
    CONSTRAINT FK_Trabaja_Medico FOREIGN KEY (registroMedico) REFERENCES Medico(registroMedico)
);

-- Crear tabla Intermedia "Especifica" (Relación ServicioSalud - OrdenServicio)
CREATE TABLE Especifica (
    idServicio VARCHAR2(20) NOT NULL,
    idOrden VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_Especifica PRIMARY KEY (idServicio, idOrden),
    CONSTRAINT FK_Especifica_ServicioSalud FOREIGN KEY (idServicio) REFERENCES ServicioSalud(idServicio),
    CONSTRAINT FK_Especifica_OrdenServicio FOREIGN KEY (idOrden) REFERENCES OrdenServicio(idOrden)
);

-- Crear tabla Intermedia "Atiende" (Relación Medico - ServicioSalud)
CREATE TABLE Atiende (
    registroMedico VARCHAR2(50) NOT NULL,
    idServicio VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_Atiende PRIMARY KEY (registroMedico, idServicio),
    CONSTRAINT FK_Atiende_Medico FOREIGN KEY (registroMedico) REFERENCES Medico(registroMedico),
    CONSTRAINT FK_Atiende_ServicioSalud FOREIGN KEY (idServicio) REFERENCES ServicioSalud(idServicio)
);

-- Crear tabla Intermedia "Prestacion" (Relación IPS - ServicioSalud)
CREATE TABLE Prestacion (
    nit VARCHAR2(20) NOT NULL,
    idServicio VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_Prestacion PRIMARY KEY (nit, idServicio),
    CONSTRAINT FK_Prestacion_IPS FOREIGN KEY (nit) REFERENCES IPS(nit),
    CONSTRAINT FK_Prestacion_ServicioSalud FOREIGN KEY (idServicio) REFERENCES ServicioSalud(idServicio)
);


CREATE SEQUENCE seq_orden_id START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_orden_id
BEFORE INSERT ON OrdenServicio
FOR EACH ROW
BEGIN
  IF :NEW.idOrden IS NULL THEN
    :NEW.idOrden := 'ORD-' || TO_CHAR(seq_orden_id.NEXTVAL);
  END IF;
END;
/

