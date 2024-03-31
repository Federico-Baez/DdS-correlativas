package domain.correlativas;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Alumno {
    private String legajo;
    private String nombre;
    private Inscripcion alternativaInscripcion;
    private Set<Materia> materiasAprobadas;

    public Alumno(String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.materiasAprobadas = new HashSet<>();
    }

    public void generarAlternativa(Materia ... asignaturas){
        this.alternativaInscripcion = new Inscripcion(asignaturas);
    }

    public Set<Materia> getMateriasAprobadas() {
        return materiasAprobadas;
    }

   public void agregarMateriasAprobadas(Materia ... asignaturas) {
       Collections.addAll(this.materiasAprobadas, asignaturas);
   }

    public Boolean anotarse(){
        return this.alternativaInscripcion.aprobada(this);
    }
}
