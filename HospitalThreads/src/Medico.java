public class Medico extends Thread{
    //======================================== ATRIBUTOS ========================================
    private final int id;
    private final Hospital hospital;
    private Paciente paciente; // paciente atual que ele está cuidando
    //========================================= MÉTODOS =========================================
    //--------------------------------------- CONSTRUTOR ----------------------------------------
    public Medico(int id, Hospital hospital) {
        this.id = id;
        this.hospital = hospital;
    }
    //---------------------------------------- GETTERS ------------------------------------------

    //---------------------------------------- GETTERS ------------------------------------------
    public void run(){
        while(true){
            paciente = hospital.nextPaciente(); // cada médico pega o próximo paciente da fila
            if(paciente==null){ // se a fila de pacientes estiver vazia, o programa encerra
                break;
            }
            while (paciente.estaVivo() && !paciente.estaCurado()){
                if (hospital.isModoSincronizado()){
                    hospital.lockAtendimento.lock();
                    try{
                        atenderPaciente();
                    }
                    finally{
                        hospital.lockAtendimento.unlock();
                    }
                }
                else
                    atenderPaciente();
            }

            if(!paciente.estaVivo() || paciente.estaCurado()) // confirma se o paciente está vivo ou curado
                hospital.removePaciente(paciente); // caso sim, remove o paciente do hospital(fila)
        }

    }

    private void atenderPaciente(){
        paciente.reduzirTempo(); // reduz o tempo de vida do paciente que está sendo atendido pelo médico
        paciente.aumentarSaude(); // aumenta a saude do paciente que está sendo atendido

        System.out.println(
                "Médico " + id +
                " atendendo o paciente " + paciente.getNome() +
                " | saude atual: " + paciente.getSaude() +
                " | tempo de vida: " + paciente.getTempoDeVida()
        );

        try{
            sleep(500); // delay de 0.5 segundo
        }
        catch(InterruptedException e){
            System.err.println("Ocorreu um erro ao tentar pausar a thread: " + e.getMessage());
        }

        if(paciente.estaCurado())
            System.out.println("Paciente " + paciente.getNome() + " foi curado!");
        else if (!paciente.estaVivo())
            System.out.println("Paciente " + paciente.getNome() + " faleceu durante o atendimento.");
    }
}
