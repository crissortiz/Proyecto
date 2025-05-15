DROP TABLE Cita CASCADE CONSTRAINTS;
DROP TABLE OrdenServicio CASCADE CONSTRAINTS;
DROP TABLE Medico CASCADE CONSTRAINTS;
DROP TABLE Afiliado CASCADE CONSTRAINTS;
DROP TABLE ServicioSalud CASCADE CONSTRAINTS;
DROP TABLE IPS CASCADE CONSTRAINTS;
DROP TABLE Trabaja CASCADE CONSTRAINTS;
DROP TABLE Especifica CASCADE CONSTRAINTS;
DROP TABLE Atiende CASCADE CONSTRAINTS;
DROP TABLE Prestacion CASCADE CONSTRAINTS;

CREATE TABLE Afiliado
    (
     idAfiliado             INTEGER  NOT NULL ,
     tipoDocumento          VARCHAR2 (100)  NOT NULL ,
     numDocumento           INTEGER  NOT NULL ,
     nombre                 VARCHAR2 (100 CHAR)  NOT NULL ,
     fechaNacimiento         DATE  NOT NULL ,
     direccion              VARCHAR2 (100 CHAR)  NOT NULL ,
     telefono               VARCHAR2 (50 CHAR)  NOT NULL ,
     tipoAfiliado           VARCHAR2 (50)  NOT NULL ,
     parentesco             VARCHAR2 (20 CHAR) ,
     afiliadoDependienteId INTEGER,
     CONSTRAINT Afiliado_PK PRIMARY KEY ( idAfiliado ),
     CONSTRAINT CK_TipoAfiliado CHECK (tipoAfiliado IN ('Beneficiario', 'Contribuyente')),
     CONSTRAINT FK_Afiliado_Dependiente FOREIGN KEY (afiliadoDependienteId) REFERENCES Afiliado(idAfiliado)
    )
;

CREATE TABLE Atiende
    (
     Medico_registroMedico1  INTEGER  NOT NULL ,
     ServicioSalud_idServicio INTEGER  NOT NULL
    )
;

ALTER TABLE Atiende
    ADD CONSTRAINT Atiende_PK PRIMARY KEY ( Medico_registroMedico1, ServicioSalud_idServicio ) ;

CREATE TABLE Cita
    (
     idCita                 INTEGER  NOT NULL ,
     fecha                  DATE  NOT NULL ,
     estadoCita             VARCHAR2 (20 CHAR)  NOT NULL ,
     OrdenServicio_idOrden  INTEGER ,
     Afiliado_idAfiliado    INTEGER  NOT NULL ,
     Medico_registroMedico1 INTEGER  NOT NULL ,
     idOrden                INTEGER
    )
;

ALTER TABLE Cita
    ADD
    CHECK (estadoCita IN ('Completa', 'Disponible', 'Ocupada'))
;

ALTER TABLE Cita
    ADD CONSTRAINT Cita_PK PRIMARY KEY ( idCita ) ;

CREATE TABLE Especifica
    (
     ServicioSalud_idServicio INTEGER  NOT NULL ,
     OrdenServicio_idOrden  INTEGER  NOT NULL
    )
;

ALTER TABLE Especifica
    ADD CONSTRAINT Especifica_PK PRIMARY KEY ( ServicioSalud_idServicio, OrdenServicio_idOrden ) ;

CREATE TABLE IPS
    (
     nit      VARCHAR2 (20 CHAR)  NOT NULL ,
     nombre   VARCHAR2 (100 CHAR)  NOT NULL ,
     direccion VARCHAR2 (200 CHAR)  NOT NULL ,
     telefono VARCHAR2 (50 CHAR)  NOT NULL
    )
;

ALTER TABLE IPS
    ADD CONSTRAINT IPS_PK PRIMARY KEY ( nit ) ;

CREATE TABLE Medico
    (
     registroMedico INTEGER  NOT NULL ,
     nombre         VARCHAR2 (200)  NOT NULL ,
     tipoDocumento  VARCHAR2 (200)  NOT NULL ,
     numDocumento   INTEGER  NOT NULL ,
     especialidad   VARCHAR2 (200)  NOT NULL
    )
;

ALTER TABLE Medico
    ADD CONSTRAINT Medico_PK PRIMARY KEY ( registroMedico ) ;

CREATE TABLE OrdenServicio
    (
     idOrden                INTEGER  NOT NULL ,
     fecha                  DATE  NOT NULL ,
     estadoOrden            VARCHAR2 (20 CHAR)  NOT NULL ,
     tipoOrden              VARCHAR2 (30)  NOT NULL ,
     descripcion            VARCHAR2 (300)  NOT NULL ,
     Medico_registroMedico1 INTEGER  NOT NULL ,
     Afiliado_idAfiliado    INTEGER  NOT NULL
    )
;

ALTER TABLE OrdenServicio
    ADD
    CHECK (estadoOrden IN ('Completada', 'Vencida', 'Vigente'))
;

ALTER TABLE OrdenServicio
    ADD
    CHECK (tipoOrden IN ('Servicio', 'Terapia'))
;

ALTER TABLE OrdenServicio
    ADD CONSTRAINT OrdenServicio_PK PRIMARY KEY ( idOrden ) ;

CREATE TABLE Prestacion
    (
     IPS_nit                VARCHAR2 (20 CHAR)  NOT NULL ,
     ServicioSalud_idServicio INTEGER  NOT NULL
    )
;

ALTER TABLE Prestacion
    ADD CONSTRAINT Prestacion_PK PRIMARY KEY ( IPS_nit, ServicioSalud_idServicio ) ;

CREATE TABLE ServicioSalud
    (
     idServicio     INTEGER  NOT NULL ,
     nombre         VARCHAR2 (100 CHAR)  NOT NULL ,
     descripcion    VARCHAR2 (300)  NOT NULL ,
     tipoServicio   VARCHAR2 (100)  NOT NULL ,
     requiereOrden  CHAR (1)  NOT NULL
    )
;

ALTER TABLE ServicioSalud
    ADD
    CHECK (tipoServicio IN ('ConsultaControl', 'ConsultaEspecialista', 'ConsultaGeneral', 'ConsultaUrgente', 'ExamenDiagnostico', 'Hospitalizacion', 'ProcedimientoEspecializado', 'Terapia'))
;

ALTER TABLE ServicioSalud
    ADD CONSTRAINT ServicioSalud_PK PRIMARY KEY ( idServicio ) ;

CREATE TABLE Trabajo
    (
     IPS_nit                VARCHAR2 (20 CHAR)  NOT NULL ,
     Medico_registroMedico1 INTEGER  NOT NULL
    )
;

ALTER TABLE Trabajo
    ADD CONSTRAINT Trabajo_PK PRIMARY KEY ( IPS_nit, Medico_registroMedico1 ) ;

ALTER TABLE Afiliado
    ADD CONSTRAINT Afiliado_Afiliado_FK FOREIGN KEY
    (
     Afiliado_Afiliado_ID
    )
    REFERENCES Afiliado
    (
     Afiliado_ID
    )
;

ALTER TABLE Afiliado
    ADD CONSTRAINT Afiliado_Afiliado_FKv1 FOREIGN KEY
    (
     Afiliado_idAfiliado
    )
    REFERENCES Afiliado
    (
     idAfiliado
    )
;

ALTER TABLE Atiende
    ADD CONSTRAINT Atiende_Medico_FK FOREIGN KEY
    (
     Medico_registroMedico1
    )
    REFERENCES Medico
    (
     registroMedico
    )
;

ALTER TABLE Atiende
    ADD CONSTRAINT Atiende_ServicioSalud_FK FOREIGN KEY
    (
     ServicioSalud_idServicio
    )
    REFERENCES ServicioSalud
    (
     idServicio
    )
;

ALTER TABLE Cita
    ADD CONSTRAINT Cita_Afiliado_FK FOREIGN KEY
    (
     Afiliado_idAfiliado
    )
    REFERENCES Afiliado
    (
     idAfiliado
    )
;



ALTER TABLE Cita
    ADD CONSTRAINT Cita_Medico_FK FOREIGN KEY
    (
     Medico_registroMedico1
    )
    REFERENCES Medico
    (
     registroMedico
    )
;

ALTER TABLE Cita
    ADD CONSTRAINT Cita_OrdenServicio_FK FOREIGN KEY
    (
     OrdenServicio_idOrden
    )
    REFERENCES OrdenServicio
    (
     idOrden
    )
;


ALTER TABLE Especifica
    ADD CONSTRAINT Especifica_OrdenServicio_FK FOREIGN KEY
    (
     OrdenServicio_idOrden
    )
    REFERENCES OrdenServicio
    (
     idOrden
    )
;

ALTER TABLE Especifica
    ADD CONSTRAINT Especifica_ServicioSalud_FK FOREIGN KEY
    (
     ServicioSalud_idServicio
    )
    REFERENCES ServicioSalud
    (
     idServicio
    )
;

ALTER TABLE OrdenServicio
    ADD CONSTRAINT OrdenServicio_Afiliado_FK FOREIGN KEY
    (
     Afiliado_idAfiliado
    )
    REFERENCES Afiliado
    (
     idAfiliado
    )
;


ALTER TABLE OrdenServicio
    ADD CONSTRAINT OrdenServicio_Medico_FK FOREIGN KEY
    (
     Medico_registroMedico1
    )
    REFERENCES Medico
    (
     registroMedico
    )
;

ALTER TABLE Prestacion
    ADD CONSTRAINT Prestacion_IPS_FK FOREIGN KEY
    (
     IPS_nit
    )
    REFERENCES IPS
    (
     nit
    )
;

ALTER TABLE Prestacion
    ADD CONSTRAINT Prestacion_ServicioSalud_FK FOREIGN KEY
    (
     ServicioSalud_idServicio
    )
    REFERENCES ServicioSalud
    (
     idServicio
    )
;

ALTER TABLE Trabajo
    ADD CONSTRAINT Trabajo_IPS_FK FOREIGN KEY
    (
     IPS_nit
    )
    REFERENCES IPS
    (
     nit
    )
;

ALTER TABLE Trabajo
    ADD CONSTRAINT Trabajo_Medico_FK FOREIGN KEY
    (
     Medico_registroMedico1
    )
    REFERENCES Medico
    (
     registroMedico
    )
;

CREATE SEQUENCE Afiliado_idAfiliado_SEQ
START WITH 1
    NOCACHE
    ORDER ;

CREATE OR REPLACE TRIGGER Afiliado_idAfiliado_TRG
BEFORE INSERT ON Afiliado
FOR EACH ROW
WHEN (NEW.idAfiliado IS NULL)
BEGIN
    :NEW.idAfiliado := Afiliado_idAfiliado_SEQ.NEXTVAL;
END;
/
                    


//Poblacion

INSERT INTO IPS (nit, nombre, direccion, telefono) VALUES ('900123456-1', 'Clínica Central', 'Cra 10 #20-30', '6011234567');
INSERT INTO IPS (nit, nombre, direccion, telefono) VALUES ('900123457-2', 'Hospital Norte', 'Av 1 #15-45', '6012345678');
INSERT INTO IPS (nit, nombre, direccion, telefono) VALUES ('900123458-3', 'Centro Médico Sur', 'Calle 50 #10-20', '6013456789');
INSERT INTO IPS (nit, nombre, direccion, telefono) VALUES ('900123459-4', 'Clínica Occidente', 'Cra 70 #80-90', '6014567890');
INSERT INTO IPS (nit, nombre, direccion, telefono) VALUES ('900123460-5', 'IPS Oriente Salud', 'Av 40 #22-33', '6015678901');

INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1001, 'Dra. Laura Gómez', 'CC', 12345678, 'Pediatría');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1002, 'Dr. Juan Pérez', 'CC', 22345678, 'Medicina Interna');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1003, 'Dr. Andrés Ruiz', 'CC', 32345678, 'Ortopedia');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1004, 'Dra. Carolina Nieto', 'CC', 42345678, 'Ginecología');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1005, 'Dr. Felipe Ríos', 'CC', 52345678, 'Dermatología');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1006, 'Dra. Natalia Díaz', 'CC', 62345678, 'Medicina General');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1007, 'Dr. Miguel Suárez', 'CC', 72345678, 'Urgencias');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1008, 'Dra. Paula Martínez', 'CC', 82345678, 'Terapia Física');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1009, 'Dr. Jaime Herrera', 'CC', 92345678, 'Neurología');
INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (1010, 'Dra. Verónica Ramírez', 'CC', 102345678, 'Psiquiatría');


INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123456-1', 1001);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123456-1', 1002);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123457-2', 1003);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123457-2', 1004);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123458-3', 1005);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123458-3', 1006);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123459-4', 1007);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123459-4', 1008);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123460-5', 1009);
INSERT INTO Trabajo (IPS_nit, Medico_registroMedico1) VALUES ('900123460-5', 1010);

INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (1, 'Consulta General', 'Consulta médica básica', 'ConsultaGeneral', 'N');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (2, 'Consulta Especialista - Pediatría', 'Atención pediátrica', 'ConsultaEspecialista', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (3, 'Consulta de Urgencias', 'Atención en urgencias', 'ConsultaUrgente', 'N');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (4, 'Examen de Sangre', 'Laboratorio clínico', 'ExamenDiagnostico', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (5, 'Radiografía', 'Imagenología básica', 'ExamenDiagnostico', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (6, 'Terapia Física', 'Sesiones de rehabilitación', 'Terapia', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (7, 'Hospitalización General', 'Internación hospitalaria', 'Hospitalizacion', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (8, 'Dermatología', 'Consulta con dermatólogo', 'ConsultaEspecialista', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (9, 'Consulta Neurología', 'Especialista en sistema nervioso', 'ConsultaEspecialista', 'S');
INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (10, 'Consulta Psiquiatría', 'Evaluación mental', 'ConsultaEspecialista', 'S');

INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123456-1', 1);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123456-1', 2);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123457-2', 3);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123457-2', 4);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123458-3', 5);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123458-3', 6);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123459-4', 7);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123459-4', 8);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123460-5', 9);
INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES ('900123460-5', 10);

INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (1, 'CC', 1100110011, 'Carlos Mendoza', TO_DATE('1980-05-12','YYYY-MM-DD'), 'Calle 1 #1-01', '3001110001', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (2, 'TI', 1100110022, 'María Mendoza', TO_DATE('2005-07-10','YYYY-MM-DD'), 'Calle 1 #1-01', '3001110001', 'Beneficiario', 'Hijo', 1);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (3, 'CC', 1100110033, 'Laura Quintero', TO_DATE('1975-08-19','YYYY-MM-DD'), 'Calle 2 #2-02', '3002220002', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (4, 'CC', 1100110044, 'Oscar Rojas', TO_DATE('1985-12-25','YYYY-MM-DD'), 'Calle 3 #3-03', '3003330003', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (5, 'TI', 1100110055, 'Sofía Rojas', TO_DATE('2010-11-11','YYYY-MM-DD'), 'Calle 3 #3-03', '3003330003', 'Beneficiario', 'Hija', 4);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (6, 'CC', 1100110066, 'Viviana Torres', TO_DATE('1990-02-28','YYYY-MM-DD'), 'Calle 4 #4-04', '3004440004', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (7, 'CC', 1100110077, 'Alejandro Díaz', TO_DATE('1972-06-06','YYYY-MM-DD'), 'Calle 5 #5-05', '3005550005', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (8, 'CC', 1100110088, 'Camila Herrera', TO_DATE('1988-03-15','YYYY-MM-DD'), 'Calle 6 #6-06', '3006660006', 'Contribuyente', NULL, NULL);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (9, 'TI', 1100110099, 'Mateo Herrera', TO_DATE('2012-09-20','YYYY-MM-DD'), 'Calle 6 #6-06', '3006660006', 'Beneficiario', 'Hijo', 8);
INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (10, 'CC', 1100110100, 'Julián Vélez', TO_DATE('1982-04-22','YYYY-MM-DD'), 'Calle 7 #7-07', '3007770007', 'Contribuyente', NULL, NULL);

INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (1, SYSDATE - 10, 'Completada', 'Servicio', 'Orden para examen de sangre', 1002, 1);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (2, SYSDATE - 5, 'Vigente', 'Terapia', 'Orden para terapia física', 1008, 3);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (3, SYSDATE - 15, 'Vencida', 'Servicio', 'Consulta con especialista en neurología', 1009, 4);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (4, SYSDATE - 3, 'Vigente', 'Servicio', 'Orden dermatología', 1005, 6);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (5, SYSDATE - 7, 'Completada', 'Terapia', 'Terapia física por accidente', 1008, 7);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (6, SYSDATE - 1, 'Vigente', 'Servicio', 'Radiografía lumbar', 1003, 1);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (7, SYSDATE - 2, 'Vigente', 'Servicio', 'Consulta psiquiatría', 1010, 8);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (8, SYSDATE - 4, 'Completada', 'Servicio', 'Consulta pediatría', 1001, 2);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (9, SYSDATE - 6, 'Vigente', 'Servicio', 'Examen de sangre general', 1002, 9);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (10, SYSDATE - 9, 'Vencida', 'Servicio', 'Consulta ginecológica', 1004, 6);

INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (11, SYSDATE +  2, 'Vigente',    'Servicio',  'Consulta General ampliada',       1001,  2);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (12, SYSDATE +  5, 'Vigente',    'Servicio',  'Consulta Especialista Pediatría',  1002,  3);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (13, SYSDATE +  7, 'Vigente',    'Terapia',   'Sesión de Terapia Física',         1008,  5);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (14, SYSDATE + 10, 'Vigente',    'Servicio',  'Examen de Sangre urgente',         1003,  1);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (15, SYSDATE + 12, 'Vigente',    'Servicio',  'Radiografía de tórax',             1003,  4);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (16, SYSDATE + 15, 'Vigente',    'Servicio',  'Consulta Dermatología',            1005,  6);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (17, SYSDATE + 18, 'Vigente',    'Terapia',   'Terapia Física avanzada',          1008,  7);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (18, SYSDATE + 20, 'Vigente',    'Servicio',  'Consulta Neurología urgente',      1009,  8);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (19, SYSDATE + 22, 'Vigente',    'Servicio',  'Consulta Psiquiatría seguimiento', 1010,  9);
INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Medico_registroMedico1, Afiliado_idAfiliado) VALUES (20, SYSDATE + 25, 'Vigente',    'Servicio',  'Hospitalización General',          1007, 10);

INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (4, 1);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (6, 2);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (9, 3);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (8, 4);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (6, 5);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (5, 6);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (10, 7);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (2, 8);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (4, 9);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (2, 10);

INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (1,  11);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (2,  12);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (6,  13);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (4,  14);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (5,  15);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (8,  16);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (6,  17);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (9,  18);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (10, 19);
INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (7,  20);


INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (1, SYSDATE - 9, 'Completa', 1, 1, 1002);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (2, SYSDATE - 5, 'Ocupada', 2, 3, 1008);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (3, SYSDATE - 12, 'Completa', 3, 4, 1009);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (4, SYSDATE - 2, 'Ocupada', 4, 6, 1005);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (5, SYSDATE - 6, 'Completa', 5, 7, 1008);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (6, SYSDATE - 1, 'Disponible', 6, 1, 1003);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (7, SYSDATE, 'Disponible', 7, 8, 1010);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (8, SYSDATE - 3, 'Completa', 8, 2, 1001);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (9, SYSDATE - 2, 'Disponible', 9, 9, 1002);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (10, SYSDATE - 10, 'Ocupada', 10, 6, 1004);

INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (11, SYSDATE +  2, 'Disponible', 11,  2, 1001);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (12, SYSDATE +  5, 'Disponible', 12,  3, 1002);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (13, SYSDATE +  7, 'Disponible', 13,  5, 1008);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (14, SYSDATE + 10, 'Disponible', 14,  1, 1003);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (15, SYSDATE + 12, 'Disponible', 15,  4, 1003);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (16, SYSDATE + 15, 'Disponible', 16,  6, 1005);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (17, SYSDATE + 18, 'Disponible', 17,  7, 1008);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (18, SYSDATE + 20, 'Disponible', 18,  8, 1009);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (19, SYSDATE + 22, 'Disponible', 19,  9, 1010);
INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Afiliado_idAfiliado, Medico_registroMedico1) VALUES (20, SYSDATE + 25, 'Disponible', 20, 10, 1007);


ALTER TABLE cita DROP COLUMN idorden;


//Eliminacion de relaciones sin sentido
DROP TABLE especifica;
DROP Table atiende;

//Modificaciones a tablas


AlTER TABLE ordenServicio ADD servicioSalud_idServicio INTEGER


-- Poblar servicioSalud_idServicio en OrdenServicio
UPDATE OrdenServicio SET servicioSalud_idServicio = 4  WHERE idOrden = 1;
UPDATE OrdenServicio SET servicioSalud_idServicio = 6  WHERE idOrden = 2;
UPDATE OrdenServicio SET servicioSalud_idServicio = 9  WHERE idOrden = 3;
UPDATE OrdenServicio SET servicioSalud_idServicio = 8  WHERE idOrden = 4;
UPDATE OrdenServicio SET servicioSalud_idServicio = 6  WHERE idOrden = 5;
UPDATE OrdenServicio SET servicioSalud_idServicio = 5  WHERE idOrden = 6;
UPDATE OrdenServicio SET servicioSalud_idServicio = 10 WHERE idOrden = 7;
UPDATE OrdenServicio SET servicioSalud_idServicio = 2  WHERE idOrden = 8;
UPDATE OrdenServicio SET servicioSalud_idServicio = 4  WHERE idOrden = 9;
UPDATE OrdenServicio SET servicioSalud_idServicio = 2  WHERE idOrden = 10;

UPDATE OrdenServicio SET servicioSalud_idServicio = 1  WHERE idOrden = 11;
UPDATE OrdenServicio SET servicioSalud_idServicio = 2  WHERE idOrden = 12;
UPDATE OrdenServicio SET servicioSalud_idServicio = 6  WHERE idOrden = 13;
UPDATE OrdenServicio SET servicioSalud_idServicio = 4  WHERE idOrden = 14;
UPDATE OrdenServicio SET servicioSalud_idServicio = 5  WHERE idOrden = 15;
UPDATE OrdenServicio SET servicioSalud_idServicio = 8  WHERE idOrden = 16;
UPDATE OrdenServicio SET servicioSalud_idServicio = 6  WHERE idOrden = 17;
UPDATE OrdenServicio SET servicioSalud_idServicio = 9  WHERE idOrden = 18;
UPDATE OrdenServicio SET servicioSalud_idServicio = 10 WHERE idOrden = 19;
UPDATE OrdenServicio SET servicioSalud_idServicio = 7  WHERE idOrden = 20;

//Nuevos RFC's

//RFC1
-- Variables de sustitución en SQL Developer:
DEFINE servicio_id = &servicio_id

SELECT
  ss.nombre               AS servicio,
  c.fecha                 AS fecha_hora,
  ip.nombre               AS ips_ofrece,
  m.nombre                AS medico
FROM Cita c
JOIN OrdenServicio os
  ON c.OrdenServicio_idOrden = os.idOrden
JOIN ServicioSalud ss
  ON os.servicioSalud_idServicio = ss.idServicio
JOIN Prestacion p
  ON ss.idServicio = p.ServicioSalud_idServicio
JOIN IPS ip
  ON p.IPS_nit = ip.nit
JOIN Medico m
  ON c.Medico_registroMedico1 = m.registroMedico
WHERE ss.idServicio   = &servicio_id
  AND c.estadoCita    = 'Disponible'
  AND c.fecha BETWEEN TRUNC(SYSDATE)
                  AND TRUNC(SYSDATE) + 28
ORDER BY c.fecha;


//RFC2
DEFINE fecha_inicio = &fecha_inicio
DEFINE fecha_fin   = &fecha_fin

SELECT *
FROM (
  SELECT
    ss.idServicio,
    ss.nombre            AS servicio,
    COUNT(*)             AS total_solicitudes
  FROM Cita c
  JOIN OrdenServicio os
    ON c.OrdenServicio_idOrden = os.idOrden
  JOIN ServicioSalud ss
    ON os.servicioSalud_idServicio = ss.idServicio
  WHERE c.fecha BETWEEN TO_DATE('&fecha_inicio','YYYY-MM-DD')
                  AND TO_DATE('&fecha_fin'   ,'YYYY-MM-DD')
    AND c.estadoCita IN ('Completa','Ocupada')
  GROUP BY ss.idServicio, ss.nombre
  ORDER BY COUNT(*) DESC
)
WHERE ROWNUM <= 20;


//RFC3
DEFINE fecha_inicio = &fecha_inicio
DEFINE fecha_fin   = &fecha_fin

WITH
  cnt_disp AS (
    SELECT
      p.ServicioSalud_idServicio AS idServicio,
      COUNT(*)                   AS disponibles
    FROM Prestacion p
    GROUP BY p.ServicioSalud_idServicio
  ),
  cnt_usados AS (
    SELECT
      os.servicioSalud_idServicio AS idServicio,
      COUNT(*)                   AS usados
    FROM Cita c
    JOIN OrdenServicio os
      ON c.OrdenServicio_idOrden = os.idOrden
    WHERE c.estadoCita = 'Completa'
      AND c.fecha BETWEEN TO_DATE('&fecha_inicio','YYYY-MM-DD')
                     AND TO_DATE('&fecha_fin'   ,'YYYY-MM-DD')
    GROUP BY os.servicioSalud_idServicio
  )
SELECT
  ss.idServicio,
  ss.nombre,
  NVL(d.disponibles,0)  AS total_disponibles,
  NVL(u.usados,0)       AS total_usados,
  CASE
    WHEN NVL(u.usados,0)=0 THEN NULL
    ELSE ROUND(d.disponibles / u.usados, 2)
  END                    AS indice_uso
FROM ServicioSalud ss
LEFT JOIN cnt_disp d
  ON ss.idServicio = d.idServicio
LEFT JOIN cnt_usados u
  ON ss.idServicio = u.idServicio
ORDER BY ss.idServicio;


//RFC4
DEFINE afiliado_id   = &afiliado_id
DEFINE fecha_inicio = &fecha_inicio
DEFINE fecha_fin    = &fecha_fin

SELECT
  ss.nombre               AS servicio,
  c.fecha                 AS fecha_tomada,
  m.nombre                AS medico,
  ip.nombre               AS ips_ofrece
FROM Cita c
JOIN OrdenServicio os
  ON c.OrdenServicio_idOrden = os.idOrden
JOIN ServicioSalud ss
  ON os.servicioSalud_idServicio = ss.idServicio
JOIN Prestacion p
  ON ss.idServicio = p.ServicioSalud_idServicio
JOIN IPS ip
  ON p.IPS_nit = ip.nit
JOIN Medico m
  ON c.Medico_registroMedico1 = m.registroMedico
WHERE c.Afiliado_idAfiliado = &afiliado_id
  AND c.estadoCita          = 'Completa'
  AND c.fecha BETWEEN TO_DATE('&fecha_inicio','YYYY-MM-DD')
                 AND TO_DATE('&fecha_fin'   ,'YYYY-MM-DD')
ORDER BY c.fecha;


UNDEFINE servicio_id
UNDEFINE fecha_inicio
UNDEFINE fecha_fin
UNDEFINE afiliado_id


COMMIT;


