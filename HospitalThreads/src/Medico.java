public class Medico {
    //======================================== ATRIBUTOS ========================================
    private int id;
    private Hospital hospital;
    //========================================= MÉTODOS =========================================
    //--------------------------------------- CONSTRUTOR ----------------------------------------
    public Medico(int id, Hospital hospital) {
        this.id = id;
        this.hospital = hospital;
    }
    //---------------------------------------- GETTERS ------------------------------------------
    public int getId() {
        return id;
    }
    public Hospital getHospital() {
        return hospital;
    }
}
