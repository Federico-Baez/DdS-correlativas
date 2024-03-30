package domain.correlativas;


public class Alumno {
    private String legajo;
    private String nombre;
    private Inscripcion alternativaInscripcion;

    public Alumno(String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public void generarAlternativa(Materia ... asignaturas){
        this.alternativaInscripcion = new Inscripcion(asignaturas);
    }

    public Boolean anotarse(){
        return this.alternativaInscripcion.aprobada();
    }
}
