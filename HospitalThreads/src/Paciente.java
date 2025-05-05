public class Paciente {
    //======================================== ATRIBUTOS ========================================
    private String nome;
    private long cpf;
    private int idade;
    private int planoSaude;
    private int saude;
    private int tempoDeVida;
    //========================================= MÉTODOS =========================================
    //---------------------------------------- Construtor ---------------------------------------
    public Paciente(String nome, long cpf, int idade, int planoSaude, int saude, int tempoDeVida) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaude = planoSaude;
        this.saude = saude;
        this.tempoDeVida = tempoDeVida;
    }
    //------------------------------------------ Getters ----------------------------------------
    //-------------------------------------- Getters :: nome ------------------------------------
    public String getNome() {
        return nome;
    }
    //---------------------------------- Getters :: plano de saude -------------------------------
    public int getPlanoSaude() {
        return planoSaude;
    }
    //-------------------------------------- Getter :: saude ------------------------------------
    public int getSaude() {
        return saude;
    }
    //---------------------------------- Getter :: tempo de vida --------------------------------
    public int getTempoDeVida() {
        return tempoDeVida;
    }
    public void reduzirTempo(){ // reduz o tempo a cada segundo que está
        tempoDeVida--;         // dentro do hospital
    }
    public void aumentarSaude(){ // aumenta a sáude em 5 a cada segundo
        saude+=10;               // durante o atendimento
    }
    public boolean estaVivo(){ // verifica se o paciente ainda está vivo (>0)
        return tempoDeVida > 0;
    }
    public boolean estaCurado(){ // verifica se ele já foi curado (>=100)
        return saude >= 100;
    }
    @Override
    public String toString() { // mostra as informações do paciente
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", idade=" + idade +
                ", planoSaude=" + planoSaude +
                ", saude=" + saude +
                ", tempoDeVida=" + tempoDeVida +
                '}';
    }
}
