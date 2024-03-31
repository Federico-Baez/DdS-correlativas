package domain.correlativas;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Materia {
    private Set<Materia> correlativas;

    public Materia(Materia ... correlativas) {
        this.correlativas = new HashSet<>();
        Collections.addAll(this.correlativas, correlativas);

    }

    public Boolean correlativasAprobadas(Alumno alumno){
        return correlativas
                .stream().
                allMatch(materia -> materia.correlativasAprobadas(alumno) &&
                        alumno.getMateriasAprobadas().stream().anyMatch(matAprobada -> matAprobada==materia));
    }
}
