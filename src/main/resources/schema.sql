-- Database: university

-- DROP DATABASE university;

CREATE TABLE IF NOT EXISTS public."department" (
  "id" INT GENERATED ALWAYS AS IDENTITY,
  "name" VARCHAR(255) NOT NULL,
  PRIMARY KEY("id")
);

CREATE TABLE IF NOT EXISTS public."professor" (
  "id" integer GENERATED ALWAYS AS IDENTITY,
  "name" VARCHAR(255) NOT NULL,
  "department_id" integer,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_department"
    FOREIGN KEY("department_id") 
	    REFERENCES department("id")
);

CREATE TABLE IF NOT EXISTS public."course" (
  "id" integer GENERATED ALWAYS AS IDENTITY,
  "name" VARCHAR(255) NOT NULL,
  "department_id" integer,
  "credits" integer NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_department"
    FOREIGN KEY("department_id") 
	    REFERENCES department("id")
);

CREATE TABLE IF NOT EXISTS public."schedule" (
  "professor_id" integer,
  "course_id" integer,
  "semester" integer NOT NULL,
  "year" integer NOT NULL,
  CONSTRAINT "fk_professor"
    FOREIGN KEY("professor_id") 
	    REFERENCES professor("id"),
  CONSTRAINT "fk_course"
    FOREIGN KEY("course_id") 
	    REFERENCES course("id")
);