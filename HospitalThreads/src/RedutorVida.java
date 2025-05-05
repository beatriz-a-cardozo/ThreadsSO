import static java.lang.Thread.sleep;

public class RedutorVida extends Thread{

    private final Hospital hospital;

    public RedutorVida(Hospital hospital) {
        this.hospital = hospital;
    }
    @Override
    public void run() {
        // Reduz o tempo de vida dos pacientes enquanto houverem pacientes no hospital
        while(true){
            hospital.checarVidas();  // Verifica se algum paciente morreu
            for (Paciente paciente : hospital.pacientes)
                paciente.reduzirTempo();  // Cada paciente perde um segundo de vida

            try{
                sleep(1000);
            }catch (InterruptedException e) {
                System.err.println("Ocorreu um erro ao tentar pausar a thread: " + e.getMessage());
            }
        }
    }
}
