public class Main {
    public static void main(String[] args) {
        Hospital hospital = new HospitalFCFS("Nova Esperanca",true);

        hospital.addPaciente(new Paciente("João", 123456789, 25, 3, 50,30));
        hospital.addPaciente(new Paciente("Maria", 987654328, 30, 1, 20,20));
        hospital.addPaciente(new Paciente("Xavier", 1233435489, 25, 3, 70,15));
        hospital.addPaciente(new Paciente("Bruna", 987600008, 40, 2, 40,10));
        hospital.addPaciente(new Paciente("Ludio", 444456789, 65, 3, 30,25));
        hospital.addPaciente(new Paciente("Caio", 922254328, 50, 1, 20,5));

        // Adicionando médicos ao hospital (digite o número de médicos desejado)
        hospital.addMedicos(2);  // Exemplo: adicionando 3 médicos

        // Iniciando a simulação (médicos começam a trabalhar)
        RedutorVida atualizadorTempoDeVida = new RedutorVida(hospital);
        atualizadorTempoDeVida.start();
        hospital.simular();

        // Mostrar número de mortos no hospital
        // System.out.println("Mortos no Hospital " + hospital.getNome() + hospital.getObitos());
    }
}
