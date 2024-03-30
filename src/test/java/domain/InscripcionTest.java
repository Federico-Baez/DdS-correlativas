package domain;

import domain.correlativas.Alumno;
import domain.correlativas.Materia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InscripcionTest {

    //testeos inscripccion aprobada/rechazada
    @Test
    public void inscripcionAprobada() {
        Materia algoritmos = new Materia(true);
        Materia discreta = new Materia(true);
        Materia analisis1 = new Materia(true);
        Materia paradigmas = new Materia(false, algoritmos, discreta);
        Materia analisis2 = new Materia(false, analisis1);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa(paradigmas, analisis2);


        Assertions.assertTrue(unAlumno.anotarse());
    }

    @Test
    public void inscripcionNoAprobada(){
        Materia algoritmos = new Materia(true);
        Materia discreta = new Materia(false);
        Materia analisis1 = new Materia(true);
        Materia paradigmas = new Materia(false, algoritmos, discreta);
        Materia analisis2 = new Materia(false, analisis1);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa(paradigmas, analisis2);


        Assertions.assertFalse(unAlumno.anotarse());
    }

    //testeo de casos borde con listas de inscripciones/correlativas vacias
    @Test
    public void inscripcionDeMateriasSinCorrelativas(){
        // el stream().allMatch() devuelve true si no hay nada en la lista de correlativas,se aprueban las correlativas, se aprueba la inscripcion
        Materia algoritmos = new Materia(false);
        Materia discreta = new Materia(false);
        Materia analisis1 = new Materia(false);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa(algoritmos, discreta, analisis1);


        Assertions.assertTrue(unAlumno.anotarse());
    }

    @Test
    public void inscripcionSinMaterias(){
        // al no incumplirse ninguna correlatividad,dado que no hay materias, se aprueba la inscripcion
        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa();


        Assertions.assertTrue(unAlumno.anotarse());
    }

    //testeo de correlativas en todos los niveles
    @Test
    public void inscripcionAprobada5Niveles() {
        //1er Nivel
        Materia algoritmos = new Materia(true);
        Materia SyO = new Materia(true);
        //2do Nivel
        Materia paradigmas = new Materia(true, algoritmos);
        Materia AdS = new Materia(true, SyO);
        //3er Nivel
        Materia diseño = new Materia(true, AdS, paradigmas);
        //4to Nivel
        Materia gestion = new Materia(true, diseño);
        //5to Nivel
        Materia proyecto = new Materia(false, gestion);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa(proyecto);


        Assertions.assertTrue(unAlumno.anotarse());
    }
    @Test
    public void inscripcionNoAprobada5Niveles() {
        //como no tengo aprobada una materia del primer nivel,
        //y por mas que las posteriores esten magicamente aprobadas, no me puedo anotar a proyecto

        //1er Nivel
        Materia algoritmos = new Materia(false);
        Materia SyO = new Materia(true);
        //2do Nivel
        Materia paradigmas = new Materia(true, algoritmos);
        Materia AdS = new Materia(true, SyO);
        //3er Nivel
        Materia diseño = new Materia(true, AdS, paradigmas);
        //4to Nivel
        Materia gestion = new Materia(true, diseño);
        //5to Nivel
        Materia proyecto = new Materia(false, gestion);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.generarAlternativa(proyecto);


        Assertions.assertFalse(unAlumno.anotarse());
    }
}
