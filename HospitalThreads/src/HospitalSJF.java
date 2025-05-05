import java.util.Comparator;

public class HospitalSJF extends Hospital{
    public HospitalSJF(String nome, boolean modoSincronizado) {
        super(nome, modoSincronizado);
    }

    public Paciente nextPaciente(){
        if(pacientes.isEmpty())
            return null;
        return pacientes.stream()
                .min(Comparator.comparingInt(Paciente::getTempoDeVida))
                .orElse(null);
    }
}
