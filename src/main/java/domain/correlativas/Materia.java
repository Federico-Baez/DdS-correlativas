package domain.correlativas;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Materia {
    private Set<Materia> correlativas;
    private Boolean aprobada;

    public Materia(Boolean aprobada, Materia ... correlativas) {
        this.correlativas = new HashSet<>();
        this.aprobada = aprobada;
        Collections.addAll(this.correlativas, correlativas);

    }

    public Boolean correlativasAprobadas(){
        return this.correlativas.stream().allMatch(materia -> materia.correlativasAprobadas() && materia.aprobada);
    }
}
