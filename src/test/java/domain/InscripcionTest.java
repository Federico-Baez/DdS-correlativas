package domain;

import domain.correlativas.Alumno;
import domain.correlativas.Materia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InscripcionTest {

    //testeos inscripccion aprobada/rechazada
    @Test
    public void inscripcionAprobada() {
        Materia algoritmos = new Materia();
        Materia discreta = new Materia();
        Materia analisis1 = new Materia();
        Materia paradigmas = new Materia(algoritmos, discreta);
        Materia analisis2 = new Materia(analisis1);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.agregarMateriasAprobadas(algoritmos, discreta, analisis1);
        unAlumno.generarAlternativa(paradigmas, analisis2);


        Assertions.assertTrue(unAlumno.anotarse());
    }

    @Test
    public void inscripcionNoAprobada(){
        Materia algoritmos = new Materia();
        Materia discreta = new Materia();
        Materia analisis1 = new Materia();
        Materia paradigmas = new Materia(algoritmos, discreta);
        Materia analisis2 = new Materia(analisis1);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.agregarMateriasAprobadas(algoritmos, analisis1);
        unAlumno.generarAlternativa(paradigmas, analisis2);


        Assertions.assertFalse(unAlumno.anotarse());
    }

    //testeo de casos borde con listas de inscripciones/correlativas vacias
    @Test
    public void inscripcionDeMateriasSinCorrelativas(){
        // el stream().allMatch() devuelve true si no hay nada en la lista de correlativas,se aprueban las correlativas, se aprueba la inscripcion
        Materia algoritmos = new Materia();
        Materia discreta = new Materia();
        Materia analisis1 = new Materia();
        Materia SyO = new Materia();

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.agregarMateriasAprobadas(SyO);
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
        Materia algoritmos = new Materia();
        Materia SyO = new Materia();
        //2do Nivel
        Materia paradigmas = new Materia(algoritmos);
        Materia AdS = new Materia(SyO);
        //3er Nivel
        Materia DdS = new Materia(AdS, paradigmas);
        //4to Nivel
        Materia gestion = new Materia(DdS);
        //5to Nivel
        Materia proyecto = new Materia(gestion);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.agregarMateriasAprobadas(algoritmos, SyO, paradigmas, AdS, DdS, gestion);
        unAlumno.generarAlternativa(proyecto);


        Assertions.assertTrue(unAlumno.anotarse());
    }
    @Test
    public void inscripcionNoAprobada5Niveles() {
        //como no tengo aprobada una materia del primer nivel,
        //y por mas que las posteriores figuren aprobadas en el alumno, no se puede anotar a proyecto

        //1er Nivel
        Materia algoritmos = new Materia();
        Materia SyO = new Materia();
        //2do Nivel
        Materia paradigmas = new Materia(algoritmos);
        Materia AdS = new Materia(SyO);
        //3er Nivel
        Materia DdS = new Materia(AdS, paradigmas);
        //4to Nivel
        Materia gestion = new Materia(DdS);
        //5to Nivel
        Materia proyecto = new Materia(gestion);

        Alumno unAlumno= new Alumno("20-333-08", "Juan");
        unAlumno.agregarMateriasAprobadas(SyO, paradigmas, AdS, DdS, gestion);
        unAlumno.generarAlternativa(proyecto);


        Assertions.assertFalse(unAlumno.anotarse());
    }
}
