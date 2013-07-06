CREATE TABLE sqlite_sequence(name,seq);
CREATE TABLE "facultad" (
    "idfacultad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreF" TEXT NOT NULL, 
	"iduniversidad" INTEGER NULL,
	FOREIGN KEY ("iduniversidad") references "universidad" ("iduniversidad"));
CREATE INDEX idfacultad on "facultad"("idfacultad");
CREATE TABLE "escuela" (
    "idescuela" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreE" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
	FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
CREATE INDEX idescuela on "escuela"("idescuela");
CREATE TABLE asignatura (
    "idasignatura" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreA" TEXT NOT NULL,
    "grupo" TEXT,
    "anio" INTEGER NOT NULL,
    "periodo" TEXT,
    "idcarrera" INTEGER NOT NULL,
    FOREIGN KEY ("idcarrera") references carrera ("idcarrera")
);
CREATE INDEX idasignatura on "asignatura"("idasignatura");
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
CREATE INDEX idestudiante on "estudiante"("idestudiante");
CREATE TABLE "tipoactividad" (
    "idtipoactividad" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "actividad" TEXT NOT NULL
);
CREATE INDEX idtipoactividad on "tipoactividad"("idtipoactividad");
CREATE TABLE "calendario" (
    "idcalendario" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "fecha" TEXT NOT NULL,
    "idtipoactividad" INTEGER NOT NULL,
    "idasignatura" INTEGER NOT NULL,
	FOREIGN KEY ("idtipoactividad") references "tipoactividad" ("idtipoactividad"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
CREATE INDEX idcalendario on "calendario"("idcalendario");
CREATE TABLE "docente" (
    "iddocente" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT NOT NULL,
    "apellido" TEXT NOT NULL,
    "usuario" TEXT NOT NULL,
    "contrasena" TEXT NOT NULL
);
CREATE INDEX iddocente on "docente"("iddocente");
CREATE VIEW "facultad_view" AS select "idfacultad","nombreF","nombreU" 
from "facultad" as f left join "universidad" as u 
on (f.iduniversidad=u.iduniversidad);
CREATE VIEW "escuela_view" AS select "idescuela","nombreE","nombreF" 
from "escuela" as e left join "facultad" as f
on (e.idfacultad=f.idfacultad);
CREATE TABLE carrera (
    "idcarrera" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombreC" TEXT NOT NULL,
    "idfacultad" INTEGER NOT NULL,
    FOREIGN KEY ("idfacultad") references "facultad" ("idfacultad")
);
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
CREATE TABLE estructuraevaluacion ( 
	idestructuraevaluacion INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 NOT NULL,
	nombreE TEXT NOT NULL,
	valor REAL NOT NULL,
	idevaluacion INTEGER NOT NULL,
	idasignatura INTEGER NOT NULL,
	FOREIGN KEY ("idevaluacion") references "evaluacion" ("idevaluacion"),
	FOREIGN KEY ("idasignatura") references "asignatura" ("idasignatura")
);
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
CREATE TABLE copia (
    "idcopia" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
);
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
CREATE INDEX iduniversidad on "universidad"("iduniversidad");
CREATE VIEW "docentea_view" AS
select "iddocente",("nombre" || " " || "apellido") as nombre,"usuario","contrasena"
from "docente";
