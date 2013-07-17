-- Describe ASIGINATURA
CREATE TABLE asignatura (
    "idasignatura" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreA" TEXT NOT NULL,
    "grupo" TEXT,
    "anio" INTEGER NOT NULL,
    "periodo" TEXT,
    "idcarrera" INTEGER NOT NULL,
    FOREIGN KEY ("idcarrera") references carrera ("idcarrera")
);
create index idasignatura on "asignatura"("idasignatura");

-- Describe ASISTENCIA
CREATE TABLE "asistencia" (
    "idasistencia" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "asistencia" TEXT NOT NULL,
    "idcalendario" INTEGER NOT NULL UNIQUE,
    "idestudiante" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL, 
	FOREIGN KEY ("idcalendario") references "calendario" ("idcalendario"),
	FOREIGN KEY ("idestudiante") references "estudiante" ("idestudiante"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
create index idasistencia on "asistencia"("idasistencia");

-- Describe CALENDARIO
CREATE TABLE "calendario" (
    "idcalendario" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "fecha" TEXT NOT NULL,
    "idtipoactividad" INTEGER NOT NULL,
	"idevaluacion" INTEGER,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idtipoactividad") references "tipoactividad" ("idtipoactividad"),
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
create index idcalendario on "calendario"("idcalendario");

-- Describe CARRERA
CREATE TABLE carrera (
    "idcarrera" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreC" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
    FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
create index idcarrera on "carrera"("idcarrera");

-- Describe DOCENTE
REATE TABLE "docente" (
    "iddocente" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT NOT NULL,
    "apellido" TEXT NOT NULL,
    "usuario" TEXT NOT NULL,
    "contrasena" TEXT NOT NULL
);
create index iddocente on "docente"("iddocente");

-- Describe ESCUELA
CREATE TABLE "escuela" (
    "idescuela" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreE" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
	FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
create index idescuela on "escuela"("idescuela");

-- Describe ESTRUCTURA EVALUACION
CREATE TABLE estructuraevaluacion ( 
	idestructuraevaluacion INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 NOT NULL,
	nombreE TEXT NOT NULL,
	valor REAL NOT NULL,
	idevaluacion INTEGER NOT NULL,
	idasignatura INTEGER NOT NULL,
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
create index idestructuraevaluacion on "estructuraevaluacion"("idestructuraevaluacion");

-- Describe ESTUDIANTE
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
create index idestudiante on "estudiante"("idestudiante");

-- Describe EVALUACION
REATE TABLE "evaluacion" (
    "idevaluacion" INTEGER NOT NULL,
    "evaluacion" TEXT NOT NULL,
FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
create index idevaluacion on "evaluacion"("idevaluacion");

-- Describe FACULTAD
CREATE TABLE "facultad" (
    "idfacultad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreF" TEXT NOT NULL, 
	"iduniversidad" INTEGER NULL,
	FOREIGN KEY ("iduniversidad") references "universidad" ("iduniversidad")
);
create index idfacultad on "facultad"("idfacultad");

-- Describe NOTAS
CREATE TABLE "notas" (
    "idnotas" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nota" REAL NOT NULL DEFAULT (0.00),
    "idevaluacion" INTEGER NOT NULL,
    "idestudiante" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idestudiante") references "estudiante" ("idestudiante"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
)
;
create index idnotas on "notas"("idnotas");

-- Describe TIPOACTIVIDAD
CREATE TABLE "tipoactividad" (
    "idtipoactividad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "actividad" TEXT NOT NULL
);
create index idtipoactividad on "tipoactividad"("idtipoactividad");

-- Describe UNIVERSIDAD
CREATE TABLE universidad (
    "iduniversidad" INTEGER NOT NULL,
    "nombreU" TEXT NOT NULL,
    "siglas" TEXT,
	"logo" BLOB
);
create index iduniversidad on "universidad"("iduniversidad");

--=======================================================================================
--
--                         VISTAS
--
--=======================================================================================

-- Describe ASIGNATURA_COMPL_VIEW
CREATE VIEW "asignatura_compl_view" AS
Select "idasignatura","nombreA","grupo","periodo","anio","nombreC","nombreF","nombreU"
from "asignatura" as a left join "carrera" as c
on (a.idcarrera=c.idcarrera) left join "facultad" as f
on (c.idfacultad=f.idfacultad) left join "universidad" as u
on (f.iduniversidad=u.iduniversidad)

-- Describe ASIGNATURA_VIEW
CREATE VIEW "asignatura_view" AS Select "idasignatura","nombreA","nombreC","grupo","periodo","anio"
from "asignatura" as a left join "carrera" as c
on (a.idcarrera=c.idcarrera)

-- Describe ASISTENCIA_VIEW
CREATE VIEW "asistencia_view" AS
select "idasistencia",("nombreE" || "apellido") as "nombre","asistencia"
from asistencia as a left join estudiante as e
on (a.idestudiante=e.idestudiante)

-- Describe CALENDARIO_VIEW
CREATE VIEW "calendario_view" AS
select "idcalendario","fecha","actividad","nombreA", e."evaluacion",c."idasignatura"
from "calendario" as c left join "tipoactividad" as ta
on (c.idtipoactividad=ta.idtipoactividad) 
left join "evaluacion" as e
on (c.idevaluacion=e.idevaluacion)
left join "asignatura" as a
on (c.idasignatura=a.idasignatura)

-- Describe CARRERA_COMPL_VIEW
CREATE VIEW "carrera_compl_view" AS
select "idcarrera","nombreC","nombreF" ,"nombreU"
from "carrera" as c left join "facultad" as f
on (c.idfacultad=f.idfacultad) left join "universidad" as u
on (f.iduniversidad=u.iduniversidad)

-- Describe CARRERA_VIEW
CREATE VIEW "carrera_view" AS select "idcarrera","nombreC","nombreF" 
from "carrera" as c left join "facultad" as f
on (c.idfacultad=f.idfacultad)

-- Describe DOCENTE_VIEW
CREATE VIEW "docente_view" AS select "iddocente","nombre","apellido","usuario","contrasena"
from "docente"

-- Describe DOCENTEA_VIEW
CREATE VIEW "docentea_view" AS
select "iddocente",("nombre" || " " || "apellido") as nombre,"usuario","contrasena"
from "docente"

-- Describe ESCUELA_VIEW
CREATE VIEW "escuela_view" AS select "idescuela","nombreE","nombreF" 
from "escuela" as e left join "facultad" as f
on (e.idfacultad=f.idfacultad)

-- Describe ESTRUCTURAEVALUACION_VIEW
CREATE VIEW "estructuraevaluacion_view" AS select "idestructuraevaluacion","nombreE","valor","evaluacion","nombreA"
from estructuraevaluacion as ee left join evaluacion as e
on (ee.idevaluacion=e.idevaluacion) left join asignatura as a
on (ee.idasignatura=a.idasignatura)

-- Describe ESTUDIANTE_VIEW
CREATE VIEW "estudiante_view" AS select "idestudiante","nombreE","apellidoE","carnet","celular",
"email","nombreA"
from "estudiante" as e left join "asignatura" as a
on (e.idasignatura=a.idasignatura)

-- Describe ESTUDIANTEA_VIEW
CREATE VIEW "estudianteA_view" AS select "idestudiante",("nombreE" || " " || "apellidoE") as nombre
from "estudiante"

-- Describe FACULTAD_VIEW
CREATE VIEW "facultad_view" AS select "idfacultad","nombreF","nombreU" 
from "facultad" as f left join "universidad" as u 
on (f.iduniversidad=u.iduniversidad)