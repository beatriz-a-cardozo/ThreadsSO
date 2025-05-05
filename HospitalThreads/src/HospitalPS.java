import java.util.Comparator;

public class HospitalPS extends Hospital{
    public HospitalPS(String nome, boolean modoSincronizado) {
        super(nome, modoSincronizado);
    }

    public Paciente nextPaciente(){
        mostrarFila();

        if(pacientes.isEmpty())
            return null;

        Paciente paciente_medico = pacientes.stream()
                    .max(Comparator.comparingInt(Paciente::getPlanoSaude))
                    .orElse(null);

        Paciente paciente_aux = pacientes.stream()
                .max(Comparator.comparingInt(Paciente::getPlanoSaude))
                .orElse(null);

        removePaciente(paciente_aux); // remove o paciente da fila do hospital

        return paciente_medico; // retorna o paciente para o médico
    }
}
