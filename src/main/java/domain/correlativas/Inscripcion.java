package domain.correlativas;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Inscripcion {
    private Set<Materia> asignaturas;

    public Inscripcion(Materia ... asignaturas) {
        this.asignaturas = new HashSet<>();
        Collections.addAll(this.asignaturas, asignaturas);
    }

    public Boolean aprobada(){
        return this.asignaturas.stream().allMatch(materia -> materia.correlativasAprobadas());
    }
}
