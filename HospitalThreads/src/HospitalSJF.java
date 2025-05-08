// == SJF : Menor Tempo Primeiro: por tempo de vida
import java.util.Comparator;

public class HospitalSJF extends Hospital{
    public HospitalSJF(String nome, boolean modoSincronizado) {
        super(nome, modoSincronizado);
    }

    public Paciente nextPaciente(){
        mostrarFila();
        if(pacientes.isEmpty())
            return null;
        if(isModoSincronizado()) {
            lockAtendimento.lock();
            try {
                Paciente paciente_medico = pacientes.stream()
                        .min(Comparator.comparingInt(Paciente::getTempoDeVida))
                        .orElse(null);

                Paciente paciente_aux = paciente_medico;

                removePaciente(paciente_aux); // remove o paciente da fila do hospital

                return paciente_medico; // retorna o paciente para o médico
            } finally {
                lockAtendimento.unlock();
            }
        }
        else{
            try {
                Thread.sleep(100); // delay inserido para demonstrar a desincronização
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Paciente paciente_medico = pacientes.stream()
                    .min(Comparator.comparingInt(Paciente::getTempoDeVida))
                    .orElse(null);

            Paciente paciente_aux = paciente_medico;

            removePaciente(paciente_aux); // remove o paciente da fila do hospital

            return paciente_medico; // retorna o paciente para o médico
        }
    }
}
