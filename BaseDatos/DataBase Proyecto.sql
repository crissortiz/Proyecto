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
                                        