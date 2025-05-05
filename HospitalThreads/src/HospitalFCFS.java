public class HospitalFCFS extends Hospital{
    public HospitalFCFS(String nome, boolean modoSincronizado) {
        super(nome,modoSincronizado);
    }
    public Paciente nextPaciente(){
        mostrarFila();
        if(pacientes.isEmpty())
            return null;

        Paciente pacienteMedico =  pacientes.get(0);

        Paciente pacienteAux = pacienteMedico; // remove o paciente da fila
        removePaciente(pacienteAux);

        return pacienteMedico;// retorna o paciente para o medico
    }// FCFS : retorna o primeiro paciente da fila
}
