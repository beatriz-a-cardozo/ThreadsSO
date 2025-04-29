import java.util.ArrayList;

public abstract class Hospital {
    //======================================== ATRIBUTOS ========================================
    private int id;
    private final ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private boolean modoSincronizado;
    private static final Object lock = new Object(); // para controle da sincronização
    //============================= MÉTODOS =============================
    //---------------------------- Construtor ---------------------------
    public Hospital(int id, boolean modoSincronizado) {
        this.id = id;
        this.modoSincronizado = modoSincronizado;
    }
    public void addPaciente(Paciente paciente){
        pacientes.add(paciente);
    }
    public void removePaciente(Paciente paciente){
        // TODO: ADICIONAR UM TRY CATCH
        pacientes.remove(paciente);
    }
    public void simular(){

    }


}
