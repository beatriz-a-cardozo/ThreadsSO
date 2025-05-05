import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Hospital{
    //======================================== ATRIBUTOS ========================================
    private final String nome;
    private int obitos;
    protected ArrayList<Paciente> pacientes = new ArrayList<>();
    private final ArrayList<Medico> medicos = new ArrayList<>();
    private final boolean modoSincronizado;
    protected Lock lockAtendimento = new ReentrantLock(); // para controle da sincronização

    //========================================= MÉTODOS =========================================
    //---------------------------------------- Construtor ---------------------------------------
    public Hospital(String nome, boolean modoSincronizado) {
        this.nome = nome;
        this.modoSincronizado = modoSincronizado;
        this.obitos  = 0;
    }
    //------------------------------------------ Getters ----------------------------------------
    //-------------------------------------- Getters :: nome ------------------------------------
    public String getNome() {
        return nome;
    }
    //------------------------------------- Getters :: mortos -----------------------------------
    public int getObitos() {
        return obitos;
    }
    //-------------------------------------- Getters :: modo ------------------------------------
    public boolean isModoSincronizado() {
        return modoSincronizado;
    }
    public void addPaciente(Paciente paciente){ //  adiciona pacientes a fila desse hospital
        pacientes.add(paciente);
    }
    public void removePaciente(Paciente paciente){ // remove um paciente da fila hospital
        pacientes.remove(paciente);
    }
    public void addMedicos(int numMedicos) {
        for (int i = 0; i < numMedicos; i++) {
            Medico medico = new Medico(i + 1, this);  // Atribui um ID ao médico
            medicos.add(medico);  // Adiciona o médico à lista de médicos
        }
    }
    public synchronized void mostrarFila() {
        if(!pacientes.isEmpty()){
            // exibe a fila de pacientes repetidamente
            System.out.println("------------------------------------------------");
            System.out.println("Fila de pacientes no Hospital " + nome + ":");
            for (Paciente paciente : pacientes) {
                System.out.println("| " + paciente.toString() + " |");
            }
            System.out.println("------------------------------------------------");
        }
        else{
            System.out.println("------------------------------------------------");
            System.out.println("O hospital esta vazio!");
            System.out.println("------------------------------------------------");
        }
    }

    public synchronized void checarVidas(){ // checa o tempo de vida de todos os pacientes da fila
        Iterator<Paciente> iterator = pacientes.iterator();
        while(iterator.hasNext()){
            Paciente paciente = iterator.next();
            if(!paciente.estaVivo()){
                obitos++;
                System.out.println("Paciente " + paciente.getNome() + " faleceu.");
                iterator.remove();
            }
        }
    }
    public abstract Paciente nextPaciente(); // obtem o próximo paciente da fila
    //----------------------------------- começa a simulação -----------------------------------
    public void simular(){ // inicia a simulação
        for (Medico medico : medicos)
            medico.start();
    }

}

