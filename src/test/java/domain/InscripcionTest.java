package domain;

import domain.correlativas.Alumno;
import domain.correlativas.Materia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InscripcionTest {

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
}
