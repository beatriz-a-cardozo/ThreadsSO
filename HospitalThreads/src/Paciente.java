public class Paciente {
    private String nome;
    private long cpf;
    private int idade;
    private int planoSaude;
    private int saude;
    private int tempoDeVida;
    public Paciente(String nome, long cpf, int idade, int planoSaude, int saude, int tempoDeVida) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaude = planoSaude;
        this.saude = saude;
        this.tempoDeVida = tempoDeVida;
    }
    public String getNome() {
        return nome;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setPlanoSaude(int planoSaude) {
        this.planoSaude = planoSaude;
    }
    public void setSaude(int saude) {
        this.saude = saude;
    }
    public void setTempoDeVida(int tempoDeVida) {
        this.tempoDeVida = tempoDeVida;
    }
    public int reduzirTempo(){ // reduz o tempo a cada segundo que está
        tempoDeVida -= 1;      // dentro do hospital
        return tempoDeVida;
    }
    public int aumentarSaude(){ // aumenta a sáude em 5 a cada segundo
        saude += 5;             // durante o atendimento
        return saude;
    }
    public boolean estaVivo(){ // verifica se o paciente está vivo (>0)
        return tempoDeVida > 0;
    }
    public boolean estaCurado(){ // verifica se ele foi curado (>=100)
        return saude >= 100;
    }
    @Override
    public String toString() {
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
