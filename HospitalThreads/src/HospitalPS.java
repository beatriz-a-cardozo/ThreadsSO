// ============================= HOSPITAL PS :: Process Status ==============================
// ---------------------- por prioridade (tempo de vida dos pacientes) ----------------------
import java.util.Comparator;

public class HospitalPS extends Hospital{                // classe filha, extende de hospital
    public HospitalPS(String nome, boolean modoSincronizado) {
        super(nome, modoSincronizado);
    }

    public Paciente nextPaciente(){
        mostrarFila();

        if(pacientes.isEmpty())
            return null;
//------------------------------------------ Métodos ----------------------------------------
        if(isModoSincronizado()){
            lockAtendimento.lock();        // usa o lock para travar a fila e evitar que dois
                                           // médicos diferentes peguem o mesmo paciente
            try{
                Paciente paciente_medico = pacientes.stream()
                        .max(Comparator.comparingInt(Paciente::getPlanoSaude))
                        .orElse(null);

                Paciente paciente_aux = paciente_medico;

                removePaciente(paciente_aux);        // remove o paciente da fila do hospital

                return paciente_medico;                   // retorna o paciente para o médico
            } finally {
                lockAtendimento.unlock();
            }
        }
        else{
            try {
                Thread.sleep(100);  // delay inserido para demonstrar a desincronização
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Paciente paciente_medico = pacientes.stream()
                    .max(Comparator.comparingInt(Paciente::getPlanoSaude))
                    .orElse(null);

            Paciente paciente_aux = pacientes.stream()
                    .max(Comparator.comparingInt(Paciente::getPlanoSaude))
                    .orElse(null);

            removePaciente(paciente_aux);

            return paciente_medico;
        }

    }
}
