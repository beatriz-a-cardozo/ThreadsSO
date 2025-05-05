public class HospitalFCFS extends Hospital{
    public HospitalFCFS(String nome, boolean modoSincronizado) {
        super(nome,modoSincronizado);
    }
    public Paciente nextPaciente(){
        mostrarFila();
        if(pacientes.isEmpty())
            return null;

        if(isModoSincronizado()){
            lockAtendimento.lock();
            try{
                Paciente pacienteMedico =  pacientes.get(0);

                Paciente pacienteAux = pacienteMedico; // remove o paciente da fila
                removePaciente(pacienteAux);

                return pacienteMedico;// retorna o paciente para o medico
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
            Paciente pacienteMedico =  pacientes.get(0);

            Paciente pacienteAux = pacienteMedico; // remove o paciente da fila
            removePaciente(pacienteAux);

            return pacienteMedico;// retorna o paciente para o medico
        }

    }// FCFS : retorna o primeiro paciente da fila
}
