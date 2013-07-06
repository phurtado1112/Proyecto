PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "facultad" (
    "idfacultad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreF" TEXT NOT NULL, 
	"iduniversidad" INTEGER NULL,
	FOREIGN KEY ("iduniversidad") references "universidad" ("iduniversidad"));
INSERT INTO "facultad" VALUES(1,'Ciencias y Sistemas',2);
INSERT INTO "facultad" VALUES(5,'Electrotécnia y Computación',1);
INSERT INTO "facultad" VALUES(6,'Ingeniería Química',1);
INSERT INTO "facultad" VALUES(7,'Ciencias Económicas y Empresariales',4);
INSERT INTO "facultad" VALUES(8,'Arquitectura',1);
INSERT INTO "facultad" VALUES(9,'Ciencias y Sistemas',1);
INSERT INTO "facultad" VALUES(10,'Ingenieria',5);
CREATE TABLE "escuela" (
    "idescuela" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreE" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
	FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
CREATE TABLE asignatura (
    "idasignatura" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreA" TEXT NOT NULL,
    "grupo" TEXT,
    "anio" INTEGER NOT NULL,
    "periodo" TEXT,
    "idcarrera" INTEGER NOT NULL,
    FOREIGN KEY ("idcarrera") references carrera ("idcarrera")
);
INSERT INTO "asignatura" VALUES(1,'Ingeniería de Software II','4TN1-S',2013,'1er Semestre',1);
INSERT INTO "asignatura" VALUES(3,'Sistemas de Información','5TN1-S',2013,'1er Semestre',1);
CREATE TABLE "estudiante" (
    "idestudiante" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreE" TEXT NOT NULL,
    "apellidoE" TEXT NOT NULL,
    "carnet" TEXT,
    "celular" INTEGER,
    "email" TEXT,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idasignatura") references asignatura ("idasignatura")
);
INSERT INTO "estudiante" VALUES(1,'Franklin','Altamirano González','','','',1);
INSERT INTO "estudiante" VALUES(2,'Luis Manuel','Berrios Rocha','',1234,'',1);
INSERT INTO "estudiante" VALUES(3,'Bonilla Rivera','Samantha Isabel','','','',1);
INSERT INTO "estudiante" VALUES(4,'Jonny Alexander','Bravo Corea','','','',1);
CREATE TABLE "tipoactividad" (
    "idtipoactividad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "actividad" TEXT NOT NULL
);
INSERT INTO "tipoactividad" VALUES(1,'Conferencia');
INSERT INTO "tipoactividad" VALUES(3,'Laboratorio');
INSERT INTO "tipoactividad" VALUES(4,'Exposición');
INSERT INTO "tipoactividad" VALUES(5,'Clase Práctica');
CREATE TABLE "calendario" (
    "idcalendario" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "fecha" TEXT NOT NULL,
    "idtipoactividad" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idtipoactividad") references "tipoactividad" ("idtipoactividad"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
INSERT INTO "calendario" VALUES(2,'15/04/2013',3,1);
INSERT INTO "calendario" VALUES(3,'16/04/2013',3,1);
INSERT INTO "calendario" VALUES(4,'18/04/2013',1,1);
CREATE TABLE "docente" (
    "iddocente" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT NOT NULL,
    "apellido" TEXT NOT NULL,
    "usuario" TEXT NOT NULL,
    "contrasena" TEXT NOT NULL
);
INSERT INTO "docente" VALUES(2,'Pablo','Hurtado','phurtado','alepa1');
CREATE TABLE carrera (
    "idcarrera" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreC" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
    FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
INSERT INTO "carrera" VALUES(1,'Ingeniería de Sistemas',1);
INSERT INTO "carrera" VALUES(3,'Ingeniería Química',6);
INSERT INTO "carrera" VALUES(5,'Ingenieria en computacion',5);
INSERT INTO "carrera" VALUES(6,'Ingenieria Electronica',5);
CREATE TABLE estructuraevaluacion ( 
	idestructuraevaluacion INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 NOT NULL,
	nombreE TEXT NOT NULL,
	valor REAL NOT NULL,
	idevaluacion INTEGER NOT NULL,
	idasignatura INTEGER NOT NULL,
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
INSERT INTO "estructuraevaluacion" VALUES(2,'1er Parcial',20.0,1,1);
INSERT INTO "estructuraevaluacion" VALUES(3,'1er Resumen',2.0,3,1);
CREATE TABLE copia (
    "idcopia" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
);
CREATE TABLE "asistencia" (
    "idasistencia" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "asistencia" TEXT NOT NULL,
    "idcalendario" INTEGER NOT NULL,
    "idestudiante" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL, 
	FOREIGN KEY ("idcalendario") references "calendario" ("idcalendario"),
	FOREIGN KEY ("idestudiante") references "estudiante" ("idestudiante"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
CREATE TABLE "evaluacion" (
    "idevaluacion" INTEGER NOT NULL,
    "evaluacion" TEXT NOT NULL,
    "idasignatura" INTEGER,
FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
CREATE TABLE "notas" (
    "idnotas" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nota" REAL NOT NULL DEFAULT (0.00),
    "idevaluacion" INTEGER NOT NULL,
    "idestudiante" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idestudiante") references "estudiante" ("idestudiante"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
CREATE TABLE universidad (
    "iduniversidad" INTEGER NOT NULL,
    "nombreU" TEXT NOT NULL,
    "siglas" TEXT
, "logo" BLOB);
INSERT INTO "universidad" VALUES(1,'Universidad Nacional de Ingeniería','UNI',NULL);
INSERT INTO "universidad" VALUES(2,'Instituto de Estudios Superiores','IES',NULL);
INSERT INTO "universidad" VALUES(4,'Universidad Centroamericana','UCA',NULL);
INSERT INTO "universidad" VALUES(5,'Universidad Politécnica de Nicaragua','UPOLI',NULL);
INSERT INTO "universidad" VALUES(6,'Universidad Americana','UAM',NULL);
INSERT INTO "universidad" VALUES(7,'Universidad Catolica Redemptoris Mater','UNICA',NULL);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('tipoactividad',5);
INSERT INTO "sqlite_sequence" VALUES('facultad',10);
INSERT INTO "sqlite_sequence" VALUES('carrera',6);
INSERT INTO "sqlite_sequence" VALUES('asignatura',3);
INSERT INTO "sqlite_sequence" VALUES('calendario',6);
INSERT INTO "sqlite_sequence" VALUES('docente',8);
INSERT INTO "sqlite_sequence" VALUES('estructuraevaluacion',3);
INSERT INTO "sqlite_sequence" VALUES('estudiante',5);
CREATE INDEX idfacultad on "facultad"("idfacultad");
CREATE INDEX idescuela on "escuela"("idescuela");
CREATE INDEX idasignatura on "asignatura"("idasignatura");
CREATE INDEX idestudiante on "estudiante"("idestudiante");
CREATE INDEX idtipoactividad on "tipoactividad"("idtipoactividad");
CREATE INDEX idcalendario on "calendario"("idcalendario");
CREATE INDEX iddocente on "docente"("iddocente");
CREATE VIEW "facultad_view" AS select "idfacultad","nombreF","nombreU" 
from "facultad" as f left join "universidad" as u 
on (f.iduniversidad=u.iduniversidad);
CREATE VIEW "escuela_view" AS select "idescuela","nombreE","nombreF" 
from "escuela" as e left join "facultad" as f
on (e.idfacultad=f.idfacultad);
CREATE INDEX idcarrera on "carrera"("idcarrera");
CREATE VIEW "carrera_view" AS select "idcarrera","nombreC","nombreF" 
from "carrera" as c left join "facultad" as f
on (c.idfacultad=f.idfacultad);
CREATE VIEW "asignatura_view" AS Select "idasignatura","nombreA","nombreC","grupo","periodo","anio"
from "asignatura" as a left join "carrera" as c
on (a.idcarrera=c.idcarrera);
CREATE VIEW "estudiante_view" AS select "idestudiante","nombreE","apellidoE","carnet","celular",
"email","nombreA"
from "estudiante" as e left join "asignatura" as a
on (e.idasignatura=a.idasignatura);
CREATE VIEW "docente_view" AS select "iddocente","nombre","apellido","usuario","contrasena"
from "docente";
CREATE VIEW "carrera_compl_view" AS
select "idcarrera","nombreC","nombreF" ,"nombreU"
from "carrera" as c left join "facultad" as f
on (c.idfacultad=f.idfacultad) left join "universidad" as u
on (f.iduniversidad=u.iduniversidad);
CREATE INDEX idestructuraevaluacion on "estructuraevaluacion"("idestructuraevaluacion");
CREATE VIEW "estructuraevaluacion_view" AS select "idestructuraevaluacion","nombreE","valor","evaluacion","nombreA"
from estructuraevaluacion as ee left join evaluacion as e
on (ee.idevaluacion=e.idevaluacion) left join asignatura as a
on (ee.idasignatura=a.idasignatura);
CREATE VIEW "asistencia_view" AS
select "idasistencia",("nombreE" || "apellido") as "nombre","asistencia"
from asistencia as a left join estudiante as e
on (a.idestudiante=e.idestudiante);
CREATE VIEW "estudianteA_view" AS select "idestudiante",("nombreE" || " " || "apellidoE") as nombre
from "estudiante";
CREATE VIEW "asignatura_compl_view" AS
Select "idasignatura","nombreA","grupo","periodo","anio","nombreC","nombreF","nombreU"
from "asignatura" as a left join "carrera" as c
on (a.idcarrera=c.idcarrera) left join "facultad" as f
on (c.idfacultad=f.idfacultad) left join "universidad" as u
on (f.iduniversidad=u.iduniversidad);
CREATE VIEW "calendario_view" AS
select "idcalendario","fecha","actividad","nombreA",c."idasignatura"
from "calendario" as c left join "tipoactividad" as ta
on (c.idtipoactividad=ta.idtipoactividad) 
left join "asignatura" as a
on (c.idasignatura=a.idasignatura);
CREATE INDEX iduniversidad on "universidad"("iduniversidad");
CREATE VIEW "docentea_view" AS
select "iddocente",("nombre" || " " || "apellido") as nombre,"usuario","contrasena"
from "docente";
COMMIT;
