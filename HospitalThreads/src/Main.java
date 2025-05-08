import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ================================ DECLARAÇÃO DE VARIÁVEIS =================================
        Scanner entrada = new Scanner(System.in);
        Hospital hospital =  null;

        String opc; // opção de scheduelling/qual hospital usar
        boolean flag = true; // flag para a sincronização
        boolean input; // variável auxiliar para a verificação de input
        boolean input2;
        char sin; // entrada de dados para a sincronia
        int num_medicos; // numero de medicos/threads

        // ==================================== ENTRADA DE DADOS ====================================
        System.out.println("[INPUT] Quantos MEDICOS deseja simular?");
        num_medicos = entrada.nextInt();

        do{
            System.out.println();
            System.out.println("[INPUT] Qual tipo de HOSPITAL(SCHEDUELLING) deseja simular?");
            System.out.println(">'FCFS' = First-Come, First-Served (ordem da fila).");
            System.out.println(">'PS' = Priority Scheduling (plano de saude).");
            System.out.println(">'SJF' = Shortest Job First (tempo de vida restante).");
            opc = entrada.next();
            System.out.println();

            System.out.println("[INPUT] deseja o MODO SINCRONIZADO (s/n)?");
            sin = entrada.next().charAt(0);

            if(sin == 's'){// verificação de input (sincronização)
                flag = true;
                input2 = true;
            }

            else if(sin == 'n'){
                flag = false;
                input2 = true;
            }

            else {
                System.out.println("[!!INVALID INPUT [MEDICO]!!]");
                input2=false;
            }

            switch(opc){                              // verificação de input (scheduelling/hospital)
                case "PS":
                    hospital = new HospitalPS("Nova Esperanca",flag);
                    input=true;
                    break;

                case "FCFS":
                    hospital = new HospitalFCFS("Santana",flag);
                    input=true;
                    break;

                case "SJF":
                    hospital = new HospitalSJF("Alberto Camilo",flag);
                    input=true;
                    break;

                default:
                    System.out.println("[!!INVALID INPUT!!]");
                    input=false;
                    break;
            }
        }while((!input)||(!input2));

        hospital.addMedicos(num_medicos);               // adiciona a quantida de desejada de medicos

        // ============================== INSERE PACIENTES PARA A FILA ==============================
        hospital.addPaciente(new Paciente("João", 14, 1, 50,20)); //1
        hospital.addPaciente(new Paciente("Maria", 27, 0, 20,6)); // 2
        hospital.addPaciente(new Paciente("Xavier", 63, 3, 90,15)); //3
        hospital.addPaciente(new Paciente("Bruna", 8, 2, 40,10)); //4
        hospital.addPaciente(new Paciente("Ludio", 45, 1, 30,10)); //5
        hospital.addPaciente(new Paciente("Caio", 90, 2, 50,7)); // 6
        hospital.addPaciente(new Paciente("Mateus", 5, 0, 20,10)); //7
        hospital.addPaciente(new Paciente("Henrique", 33, 3, 80,20)); // 8
        //hospital.addPaciente(new Paciente("Mariana", 71, 1, 10,5)); //9
        //hospital.addPaciente(new Paciente("Helena", 52, 0, 50,8)); // 10
        //hospital.addPaciente(new Paciente("Luisa", 19, 2, 10,5)); // 11
        //hospital.addPaciente(new Paciente("Leo", 88, 3, 30,20)); // 12

        //=================================== INICIA A SIMULAÇÃO ====================================
        // iniciando a simulação (médicos começam a trabalhar)
        RedutorVida atualizadorTempoDeVida = new RedutorVida(hospital); // inicia a thread redutora
        atualizadorTempoDeVida.start();                                 // de tempo para todos os
                                                                        // pacientes

        hospital.simular();          // inicia a(s) threads dos médicos / MEDICOS COMEÇAM A TRABALHAR

    }
}
